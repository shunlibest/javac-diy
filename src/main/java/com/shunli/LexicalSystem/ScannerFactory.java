/*
 * Copyright (c) 1999, 2010, Oracle and/or its affiliates. All rights reserved.
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


import com.shunli.LexicalSystem.token.Tokens;
import com.sun.tools.javac.parser.JavadocTokenizer;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import java.nio.CharBuffer;


public class ScannerFactory {

    private static ScannerFactory instance = null;
    public static Context context = new Context();

    public static ScannerFactory instance() {
        if (instance == null)
            instance = new ScannerFactory();
        return instance;
    }

    final Names names;
    final Tokens tokens;

    /**
     * Create a new scanner factory.
     */
    protected ScannerFactory() {
        this.names = Names.instance(context);
        this.tokens = Tokens.instance();
    }

    public Scanner newScanner(CharSequence input, boolean keepDocComments) {
        if (input instanceof CharBuffer) {
            CharBuffer buf = (CharBuffer) input;
//            if (keepDocComments)
//                return new Scanner(this, new JavadocTokenizer(this, buf));
//            else
                return new Scanner(this, buf);
        } else {
            char[] array = input.toString().toCharArray();
            return newScanner(array, array.length, keepDocComments);
        }
    }

    public Scanner newScanner(char[] input, int inputLength, boolean keepDocComments) {
//        if (keepDocComments)
//            return new Scanner(this, new JavadocTokenizer(this, input, inputLength));
//        else
            return new Scanner(this, input, inputLength);
    }
}
