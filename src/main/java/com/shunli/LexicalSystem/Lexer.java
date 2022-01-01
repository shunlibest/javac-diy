/*
 * Copyright (c) 2005, 2012, Oracle and/or its affiliates. All rights reserved.
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


import com.shunli.LexicalSystem.token.Token;
import com.sun.tools.javac.util.Position;

/**
 * 词法分析器: 将字符串转为Token流
 */
public interface Lexer {

    /**
     * Consume the next token.
     */
    void nextToken();

    //返回当前的token
    Token token();

    //返回token
    Token token(int lookahead);

    /**
     * Return the last character position of the previous token.
     */
    Token prevToken();

    //将当前令牌一分为二并返回第一个（拆分的）令牌。 例如 '<<<' 分别被分成两个标记 '<' 和 '<<'，后者被返回。
    Token split();

    //返回发生词法错误的位置
    int errPos();

    //设置发生词法错误的位置
    void errPos(int pos);

    //构建用于在输入中的行号和位置之间进行转换的映射。
    Position.LineMap getLineMap();
}
