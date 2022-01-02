/*
 * Copyright (c) 1999, 2012, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.shunli.LexicalSystem.name;

import com.sun.tools.javac.util.ArrayUtils;
import com.sun.tools.javac.util.Convert;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * Name.Table 的实现将所有名称存储在单个共享字节数组中，并根据需要对其进行扩展。
 * 这避免了为每个名称使用字节数组所产生的开销。
 */
public class SharedNameTable extends Table {
    // 回收的列表, 用于重用
    private static LinkedList<SoftReference<SharedNameTable>> freelist = new LinkedList<>();

    static public synchronized SharedNameTable create(Names names) {
        while (!freelist.isEmpty()) {
            SharedNameTable t = freelist.poll().get();
            if (t != null) {
                return t;
            }
        }
        return new SharedNameTable(names);
    }

    static private synchronized void dispose(SharedNameTable t) {
        freelist.addFirst(new SoftReference<>(t));
    }

    //Name集合组成的hashTable
    private NameImpl[] hashes;

    //保存所有遇到的名称的共享字节数组
    public byte[] bytes;

    //
    private int hashMask;

    //`names' 中填充的字节数。
    private int nc = 0;

    //初始化hash表
    public SharedNameTable(Names names, int hashSize, int nameSize) {
        super(names);
        hashMask = hashSize - 1;
        hashes = new NameImpl[hashSize];
        bytes = new byte[nameSize];

    }

    public SharedNameTable(Names names) {
        this(names, 0x8000, 0x20000);
    }

    @Override
    public Name fromChars(char[] cs, int start, int len) {
        int nc = this.nc;
        byte[] bytes = this.bytes = ArrayUtils.ensureCapacity(this.bytes, nc + len * 3);
        int nbytes = Convert.chars2utf(cs, start, bytes, nc, len) - nc;
        int h = hashValue(bytes, nc, nbytes) & hashMask;
        NameImpl n = hashes[h];
        while (n != null &&
                (n.getByteLength() != nbytes ||
                        !equals(bytes, n.index, bytes, nc, nbytes))) {
            n = n.next;
        }
        if (n == null) {
            n = new NameImpl(this);
            n.index = nc;
            n.length = nbytes;
            n.next = hashes[h];
            hashes[h] = n;
            this.nc = nc + nbytes;
            if (nbytes == 0) {
                this.nc++;
            }
        }
        return n;
    }

    @Override
    public Name fromUtf(byte[] cs, int start, int len) {
        int h = hashValue(cs, start, len) & hashMask;
        NameImpl n = hashes[h];
        byte[] names = this.bytes;
        while (n != null &&
                (n.getByteLength() != len || !equals(names, n.index, cs, start, len))) {
            n = n.next;
        }
        if (n == null) {
            int nc = this.nc;
            names = this.bytes = ArrayUtils.ensureCapacity(names, nc + len);
            System.arraycopy(cs, start, names, nc, len);
            n = new NameImpl(this);
            n.index = nc;
            n.length = len;
            n.next = hashes[h];
            hashes[h] = n;
            this.nc = nc + len;
            if (len == 0) {
                this.nc++;
            }
        }
        return n;
    }

    @Override
    public void dispose() {
        dispose(this);
    }

}
