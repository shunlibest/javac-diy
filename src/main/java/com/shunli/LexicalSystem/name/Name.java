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

import com.sun.tools.javac.util.Convert;

/**
 * 可以当成是String类型, 存储字符串, 多数方法, 直接使用String的方法
 * 名称存储在 Name.Table 中，并且在该表中是唯一的
 */
public abstract class Name implements javax.lang.model.element.Name {

    public final Table table;

    protected Name(Table table) {
        this.table = table;
    }

    @Override
    public boolean contentEquals(CharSequence cs) {
        return toString().equals(cs.toString());
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public String toString() {
        return Convert.utf2string(getByteArray(), getByteOffset(), getByteLength());
    }


    //在字符串常量表中的索引值
    public abstract int getIndex();

    //获取此名称的字节长度
    public abstract int getByteLength();

    //返回第 i 个字节的值
    public abstract byte getByteAt(int i);

    //获取此名称的底层字节数组
    public abstract byte[] getByteArray();

    //获取此名称在其字节数组中的起始偏移量。
    public abstract int getByteOffset();


}
