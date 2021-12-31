/*
 * Copyright (c) 2005, 2009, Oracle and/or its affiliates. All rights reserved.
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

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import java.io.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.CharsetDecoder;


public abstract class BaseFileObject implements JavaFileObject {

    /**
     * 文件类型
     *
     * @return 固定为class文件
     */
    @Override
    public Kind getKind() {
        return Kind.CLASS;
    }

    @Override
    public boolean isNameCompatible(String simpleName, Kind kind) {
        return true;
    }

    @Override
    public NestingKind getNestingKind() {
        return NestingKind.TOP_LEVEL;
    }

    @Override
    public Modifier getAccessLevel() {
        return Modifier.PUBLIC;
    }

//    @Override
//    public URI toUri() {
//        return null;
//    }

    @Override
    public String getName() {
        return "BaseFileObject getName";
    }


    @Override
    public boolean delete() {
        return false;
    }

    //
//
    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        return new InputStreamReader(openInputStream(), FileUtil.getEncodingName());
    }
//
//    protected CharsetDecoder getDecoder(boolean ignoreEncodingErrors) {
//        throw new UnsupportedOperationException();
//    }
//
//    protected abstract String inferBinaryName(Iterable<? extends File> path);
//
//    protected static Kind getKind(String filename) {
//        return BaseFileManager.getKind(filename);
//    }
//
//    protected static String removeExtension(String fileName) {
//        int lastDot = fileName.lastIndexOf(".");
//        return (lastDot == -1 ? fileName : fileName.substring(0, lastDot));
//    }


}
