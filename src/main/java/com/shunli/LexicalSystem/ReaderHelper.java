/*
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
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

package com.shunli.LexicalSystem;


import com.shunli.LexicalSystem.name.Names;


import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;


/**
 * javac 词法分析器/标记器使用的字符读取器。 返回输入流中包含的字符序列，相应地处理Unicode
 */
public class ReaderHelper {
    public static final char EOF = '\u0000';
    //输入缓冲区
    protected char[] buf;
    protected int bp;
    protected int buflen;

    //当前字符
    protected char ch;

    //最后转换的 unicode 字符的缓冲区索引
    protected int unicodeConversionBp = -1;

    protected Names names;

    //用于保存字符的字符缓冲区。
    protected char[] sbuf = new char[128];
    protected int sp;

    //BufferedReader是可以按行读取文件
    BufferedReader bufferedReader;


    public ReaderHelper(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            readNewLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean readNewLine() {
        try {
            String str = bufferedReader.readLine();
            if (str == null) {
                return false;
            }
            buf = str.toCharArray();
            buflen = str.length();
            bp = -1;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    //读取下一个字符
    protected char readNewChar() {
        if (bp < buflen - 1) {
            ch = buf[++bp];
            if (ch == '\\') {
//                convertUnicode();
            }
        } else {
            bp++;
            ch = EOF;
        }
        return ch;
    }

    public void unReadChar() {
        bp--;
        ch = buf[bp];
    }


    public String getString(int startPos, int endPos) {
        StringBuilder stringBuilder = new StringBuilder(endPos - startPos);
        for (int i = startPos; i <= endPos; i++) {
            stringBuilder.append(buf[i]);
        }
        return stringBuilder.toString();
    }

    public char peek() {
        return buf[bp + 1];
    }
}
