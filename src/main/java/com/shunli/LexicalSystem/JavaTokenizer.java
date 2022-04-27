/*
 * Copyright (c) 1999, 2013, Oracle and/or its affiliates. All rights reserved.
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


import com.shunli.LexicalSystem.name.Name;
import com.shunli.LexicalSystem.token.*;
import com.shunli.utils.Log;
import org.jetbrains.annotations.Nullable;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import static com.shunli.LexicalSystem.ReaderHelper.EOF;


public class JavaTokenizer {

    private ArrayList<Token> tokenQueue = new ArrayList<Token>();


    protected ReaderHelper reader;

    public JavaTokenizer(ReaderHelper readerHelper) {
        this.reader = readerHelper;
    }

    //读取一个新的token
    @Nullable
    public Token readToken() {
        reader.sp = 0;

        int startPos;
        int endPos;


        char ch = reader.readNewChar();

        if (ch == EOF) {
            if (reader.readNewLine()) {
                return readToken();
            }
            return null;
        }


        //空白case, 直接过滤
        if (ch == ' ' | ch == '\t') {
            do {
                reader.readNewChar();
            } while (reader.ch == ' ' || reader.ch == '\t');
        }

        if (LexicalAnalyzerUtil.isLetter(reader.ch)) { // ID识别
            startPos = reader.bp;
            do {
                reader.readNewChar();
            } while (LexicalAnalyzerUtil.isLetterOrNum(reader.ch));
            reader.unReadChar();
            endPos = reader.bp;
            return new IdToken(1, reader.getString(startPos, endPos));
        } else if (LexicalAnalyzerUtil.isNum(reader.ch)) { // 数字识别
            startPos = reader.bp;
            do {
                reader.readNewChar();
            } while (LexicalAnalyzerUtil.isLetterOrNum(reader.ch) || reader.ch == '.');
            reader.unReadChar();
            endPos = reader.bp;
            String string = reader.getString(startPos, endPos);
            if (string.contains(".")) {
                float num = LexicalAnalyzerUtil.stringToFloatNum(string);
                return new FloatNumToken(1, num);
            } else {
                int num = LexicalAnalyzerUtil.stringToNum(string);
                return new IntNumToken(1, num);
            }
        } else { //剩下的肯定就是特殊符号了
            return handlerSpecialChar();
        }
    }

    @Nullable
    private Token handlerSpecialChar() {
        String boundaryChar = "[](){}:,.;";
        //TODO 转义符
        if (boundaryChar.contains("" + reader.ch)) {
            return new SpecialCharToken(1, reader.ch);
        }
        int startPos = 0;
        int endPos = 0;

        // 单引号和双引号
        if (reader.ch == '\'') {
            if (reader.peek() == '\'') {
                throw new RuntimeException(" 不能两个' 连着用");
            }
            char c = reader.readNewChar();
            if (reader.readNewChar() != '\'') {
                throw new RuntimeException(" 两个' 之间只能有一个char");
            }
            return new StrToken(1, "" + c);
        }

        if (reader.ch == '\"') {
            startPos = reader.bp + 1;
            do {
                reader.readNewChar();
            } while (reader.ch != '\"');
            endPos = reader.bp - 1;
            return new IdToken(1, reader.getString(startPos, endPos));
        }


        //+-三种情况: [+ ++ +=] [- -- -=]
        if (reader.ch == '+' || reader.ch == '-') {
            char nextChar = reader.peek();
            if (nextChar == '+' || nextChar == '=') {
                char c = reader.readNewChar();
                return new SpecialCharToken(1, "" + reader.ch + c);
            }
            return new SpecialCharToken(1, reader.ch);
        }
        //*只有两种case: * *=
        // TODO 多行注释
        // %= %s等
        if (reader.ch == '*') {
            if (reader.peek() == '=') {
                char c = reader.readNewChar();
                return new SpecialCharToken(1, "" + reader.ch + c);
            }
            return new SpecialCharToken(1, reader.ch);
        }
        //除法, 会遇到注释的情况
        if (reader.ch == '/') {
            if (reader.peek() == '/') {
                reader.readNewLine();
                return readToken();
            }
            return new SpecialCharToken(1, reader.ch);
        }

        // == 和 !=
        if (reader.ch == '=' || reader.ch == '!') {
            if (reader.peek() == '=') {
                char c = reader.readNewChar();
                return new SpecialCharToken(1, "" + reader.ch + c);
            }
            return new SpecialCharToken(1, reader.ch);
        }

        // > >= 和 < <=
        if (reader.ch == '>' || reader.ch == '<') {
            if (reader.peek() == '=') {
                char c = reader.readNewChar();
                return new SpecialCharToken(1, "" + reader.ch + c);
            }
            return new SpecialCharToken(1, reader.ch);
        }


        throw new RuntimeException("handlerSpecialChar error, char is " + reader.ch);
//        return null;
    }

}
