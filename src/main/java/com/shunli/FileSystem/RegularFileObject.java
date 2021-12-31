/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
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

package com.shunli.FileSystem;

import java.io.File;
import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class RegularFileObject extends BaseFileObject {
    final File file;

    public RegularFileObject(File f) {
        this.file = f;
    }

    @Override
    public URI toUri() {
        return file.toURI().normalize();
    }

    @Override
    public String getName() {
        return file.getPath();
    }


    @Override
    public InputStream openInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return new FileOutputStream(file);
    }

    @Override
    public CharBuffer getCharContent(boolean ignoreEncodingErrors) throws IOException {
        CharBuffer cb = null;
        InputStream in = new FileInputStream(file);
        try {
            ByteBuffer bb = FileUtil.makeByteBuffer(in);
            cb = FileUtil.decode(bb, ignoreEncodingErrors);
        } finally {
            in.close();
        }
        return cb;
    }

    @Override
    public Writer openWriter() throws IOException {
        return new OutputStreamWriter(new FileOutputStream(file), FileUtil.getEncodingName());
    }

    @Override
    public long getLastModified() {
        return file.lastModified();
    }

}
