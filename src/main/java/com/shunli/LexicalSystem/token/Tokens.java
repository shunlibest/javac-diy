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

package com.shunli.LexicalSystem.token;


import com.sun.tools.javac.util.*;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import java.util.Locale;

/**
 * 返回token流
 */
public class Tokens {

    private final Names names;

    //关键字数组。 将名称索引映射到令牌。
    private final TokenKind[] key;

    //关键字数组。 将名称索引映射到令牌。
    private int maxKey = 0;

    //所有Token的名字
    private Name[] tokenName = new Name[TokenKind.values().length];
    private static final Context context = new Context();
    //单例
    private static Tokens instance = null;

    public static Tokens instance() {
        if (instance == null)
            instance = new Tokens();
        return instance;
    }

    protected Tokens() {
        names = Names.instance(context);
        for (TokenKind t : TokenKind.values()) {
            if (t.name != null)
                enterKeyword(t.name, t);
            else
                tokenName[t.ordinal()] = null;
        }

        key = new TokenKind[maxKey + 1];
        for (int i = 0; i <= maxKey; i++) key[i] = TokenKind.IDENTIFIER;
        for (TokenKind t : TokenKind.values()) {
            if (t.name != null)
                key[tokenName[t.ordinal()].getIndex()] = t;
        }
    }

    private void enterKeyword(String s, TokenKind token) {
        Name n = names.fromString(s);
        tokenName[token.ordinal()] = n;
        if (n.getIndex() > maxKey) maxKey = n.getIndex();
    }

    /**
     * Create a new token given a name; if the name corresponds to a token name,
     * a new token of the corresponding kind is returned; otherwise, an
     * identifier token is returned.
     */
    public TokenKind lookupKind(Name name) {
//        return lookupKind(name.toString());
        if (name.toString() .equals("=")){
            return lookupKind("=");
        }
        return (name.getIndex() > maxKey) ? TokenKind.IDENTIFIER : key[name.getIndex()];
    }

    TokenKind lookupKind(String name) {
        return lookupKind(names.fromString(name));
    }


    public interface Comment {

        enum CommentStyle {
            LINE,
            BLOCK,
            JAVADOC,
        }

        String getText();

        int getSourcePos(int index);

        CommentStyle getStyle();

        boolean isDeprecated();
    }


    public static final Token DUMMY =
            new Token(TokenKind.ERROR, 0, 0, null);
}
