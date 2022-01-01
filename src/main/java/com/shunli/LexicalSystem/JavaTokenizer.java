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


import com.shunli.LexicalSystem.token.*;
import com.shunli.utils.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Position;

import java.nio.CharBuffer;
import java.util.List;


public class JavaTokenizer {

    final static byte LF = 0xA;
    final static byte FF = 0xC;
    final static byte CR = 0xD;
    final static byte EOI = 0x1A;

    private static final boolean scannerDebug = true;


    //log日志
    private static final Log log = new Log();

    /**
     * The token factory.
     */
    private final Tokens tokens;

    /**
     * The token kind, set by nextToken().
     */
    protected TokenKind tk;


    /**
     * The token's name, set by nextToken().
     */
    protected Name name;

    /**
     * The position where a lexical error occurred;
     */
    protected int errPos = Position.NOPOS;

    /**
     * The Unicode reader (low-level stream reader).
     */
    protected UnicodeReader reader;

    protected ScannerFactory fac;


    /**
     * Create a scanner from the input array.  This method might
     * modify the array.  To avoid copying the input array, ensure
     * that {@code inputLength < input.length} or
     * {@code input[input.length -1]} is a white space character.
     *
     * @param fac the factory which created this Scanner
     * @param buf the input, might be modified
     *            Must be positive and less than or equal to input.length.
     */
    protected JavaTokenizer(ScannerFactory fac, CharBuffer buf) {
        this(fac, new UnicodeReader(fac, buf));
    }

    protected JavaTokenizer(ScannerFactory fac, char[] buf, int inputLength) {
        this(fac, new UnicodeReader(fac, buf, inputLength));
    }

    protected JavaTokenizer(ScannerFactory fac, UnicodeReader reader) {
        this.fac = fac;
        this.tokens = fac.tokens;
        this.reader = reader;
    }


    protected void lexError(int pos, String key, Object... args) {
        log.error(pos, key, args);
        tk = TokenKind.ERROR;
        errPos = pos;
    }

    /**
     * 读取字符或字符串文字中的下一个字符并复制到 sbuf。
     */
    private void scanLitChar(int pos) {
        if (reader.bp != reader.buflen) {
            reader.putChar(true);
        }
    }

    private void scanDigits(int pos, int digitRadix) {
        do {
            reader.putChar(false);
            reader.scanChar();
        } while (reader.digit(pos, digitRadix) >= 0);

    }


    /**
     * Read fractional part of floating point number.
     */
    private void scanFraction(int pos) {
        skipIllegalUnderscores();
        if ('0' <= reader.ch && reader.ch <= '9') {
            scanDigits(pos, 10);
        }
        int sp1 = reader.sp;
        if (reader.ch == 'e' || reader.ch == 'E') {
            reader.putChar(true);
            skipIllegalUnderscores();
            if (reader.ch == '+' || reader.ch == '-') {
                reader.putChar(true);
            }
            skipIllegalUnderscores();
            if ('0' <= reader.ch && reader.ch <= '9') {
                scanDigits(pos, 10);
                return;
            }
            lexError(pos, "malformed.fp.lit");
            reader.sp = sp1;
        }
    }

    /**
     * Read fractional part and 'd' or 'f' suffix of floating point number.
     */
    private void scanFractionAndSuffix(int pos) {
        scanFraction(pos);
        tk = TokenKind.DOUBLELITERAL;
    }


    private void skipIllegalUnderscores() {
        if (reader.ch == '_') {
            lexError(reader.bp, "illegal.underscore");
            while (reader.ch == '_')
                reader.scanChar();
        }
    }

    /**
     * 读取数字, 仅支持十进制数字
     */
    private void scanNumber(int pos, int radix) {
        int digitRadix = 10;
        boolean seendigit = false;
        //读取小数点前面的整数
        if (reader.digit(pos, digitRadix) >= 0) {
            scanDigits(pos, digitRadix);
        }
        //如果有小数点, 则代表该数字是浮点数, 否则是整数
        if (reader.ch == '.') {
            reader.putChar(true);
            scanFractionAndSuffix(pos);
        } else {
            tk = TokenKind.INTLITERAL;
        }
    }

    //读取到标识符
    private void scanIdent() {
        reader.putChar(true);
        do {
            switch (reader.ch) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                case '$':
                case '_':
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                case '\u0000':
                case '\u0001':
                case '\u0002':
                case '\u0003':
                case '\u0004':
                case '\u0005':
                case '\u0006':
                case '\u0007':
                case '\u0008':
                case '\u000E':
                case '\u000F':
                case '\u0010':
                case '\u0011':
                case '\u0012':
                case '\u0013':
                case '\u0014':
                case '\u0015':
                case '\u0016':
                case '\u0017':
                case '\u0018':
                case '\u0019':
                case '\u001B':
                case '\u007F':
                    reader.scanChar();
                    continue;
                case '\u001A': // EOI is also a legal identifier part
                    if (reader.bp >= reader.buflen) {
                        name = reader.name();
                        tk = tokens.lookupKind(name);
                        return;
                    }
                    reader.scanChar();
                    continue;
                default:
                    // all ASCII range chars already handled, above
                    name = reader.name();
                    tk = tokens.lookupKind(name);
                    return;
            }
            reader.putChar(true);
        } while (true);
    }

    /**
     * Return true if reader.ch can be part of an operator.
     */
    private boolean isSpecial(char ch) {
        switch (ch) {
            case '!':
            case '%':
            case '&':
            case '*':
            case '?':
            case '+':
            case '-':
            case ':':
            case '<':
            case '=':
            case '>':
            case '^':
            case '|':
            case '~':
            case '@':
                return true;
            default:
                return false;
        }
    }

    /**
     * Read longest possible sequence of special characters and convert
     * to token.
     */
    private void scanOperator() {
        while (true) {
            reader.putChar(false);
            Name newname = reader.name();
            TokenKind tk1 = tokens.lookupKind(newname);
            if (tk1 == TokenKind.IDENTIFIER) {
                reader.sp--;
                break;
            }
            tk = tk1;
            reader.scanChar();
            if (!isSpecial(reader.ch)) break;
        }
    }

    //读取一个新的token
    public Token readToken() {
        reader.sp = 0;
        name = null;

        int pos = 0;
        int endPos = 0;
        List<Tokens.Comment> comments = null;

        try {
            loop:
            //循环读取char
            while (true) {
                pos = reader.bp;
                switch (reader.ch) {
                    //类型A: 特殊字符
                    //A-1:
                    case ' ':       //空格
                    case '\t':      //水平制表符
                    case FF:        //换行/换页符
                        do {
                            reader.scanChar();
                        } while (reader.ch == ' ' || reader.ch == '\t' || reader.ch == FF);
                        processWhiteSpace(pos, reader.bp);
                        break;
                    case LF: // (Spec 3.4)
                        reader.scanChar();
                        processLineTerminator(pos, reader.bp);
                        break;
                    case CR: // (Spec 3.4)
                        reader.scanChar();
                        if (reader.ch == LF) {
                            reader.scanChar();
                        }
                        processLineTerminator(pos, reader.bp);
                        break;
                    //类型B: 标识符(包名,类名,变量名)
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'E':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    case 'e':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'm':
                    case 'n':
                    case 'o':
                    case 'p':
                    case 'q':
                    case 'r':
                    case 's':
                    case 't':
                    case 'u':
                    case 'v':
                    case 'w':
                    case 'x':
                    case 'y':
                    case 'z':
                    case '$':
                    case '_':
                        scanIdent();
                        break loop;

                    case '0':
                        //TODO : 仅支持十进制数字
//                        throw new Exception("仅支持十进制数字");
                        break loop;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        scanNumber(pos, 10);
                        break loop;
                    case '.':
                        reader.scanChar();
                        if ('0' <= reader.ch && reader.ch <= '9') {
                            reader.putChar('.');
                            scanFractionAndSuffix(pos);
                        } else if (reader.ch == '.') {
                            int savePos = reader.bp;
                            reader.putChar('.');
                            reader.putChar('.', true);
                            if (reader.ch == '.') {
                                reader.scanChar();
                                reader.putChar('.');
                                tk = TokenKind.ELLIPSIS;
                            } else {
                                lexError(savePos, "illegal.dot");
                            }
                        } else {
                            tk = TokenKind.DOT;
                        }
                        break loop;
                    case ',':
                        reader.scanChar();
                        tk = TokenKind.COMMA;
                        break loop;
                    case ';':
                        reader.scanChar();
                        tk = TokenKind.SEMI;
                        break loop;
                    case '(':
                        reader.scanChar();
                        tk = TokenKind.LPAREN;
                        break loop;
                    case ')':
                        reader.scanChar();
                        tk = TokenKind.RPAREN;
                        break loop;
                    case '[':
                        reader.scanChar();
                        tk = TokenKind.LBRACKET;
                        break loop;
                    case ']':
                        reader.scanChar();
                        tk = TokenKind.RBRACKET;
                        break loop;
                    case '{':
                        reader.scanChar();
                        tk = TokenKind.LBRACE;
                        break loop;
                    case '}':
                        reader.scanChar();
                        tk = TokenKind.RBRACE;
                        break loop;
                    case '/':
                        //TODO 不支持注释
                        break loop;
                    case '\'':
                        reader.scanChar();
                        if (reader.ch == '\'') {
                            lexError(pos, "empty.char.lit");
                        } else {
                            if (reader.ch == CR || reader.ch == LF)
                                lexError(pos, "illegal.line.end.in.char.lit");
                            scanLitChar(pos);
                            char ch2 = reader.ch;
                            if (reader.ch == '\'') {
                                reader.scanChar();
                                tk = TokenKind.CHARLITERAL;
                            } else {
                                lexError(pos, "unclosed.char.lit");
                            }
                        }
                        break loop;
                    case '\"':
                        reader.scanChar();
                        while (reader.ch != '\"' && reader.ch != CR && reader.ch != LF && reader.bp < reader.buflen)
                            scanLitChar(pos);
                        if (reader.ch == '\"') {
                            tk = TokenKind.STRINGLITERAL;
                            reader.scanChar();
                        } else {
                            lexError(pos, "unclosed.str.lit");
                        }
                        break loop;
                    default:
                        if (isSpecial(reader.ch)) {
                            scanOperator();
                        } else {
                            if (reader.bp == reader.buflen || reader.ch == EOI && reader.bp + 1 == reader.buflen) { // JLS 3.5
                                tk = TokenKind.EOF;
                                pos = reader.buflen;
                            } else {
                                String arg = (32 < reader.ch && reader.ch < 127) ?
                                        String.format("%s", reader.ch) :
                                        String.format("\\u%04x", (int) reader.ch);
                                lexError(pos, "illegal.char", arg);
                                reader.scanChar();
                            }
                        }
                        break loop;
                }
            }
            endPos = reader.bp;
            switch (tk.tag) {
                case DEFAULT:
                    return new Token(tk, pos, endPos, comments);
                case NAMED:
                    return new NamedToken(tk, pos, endPos, name, comments);
                case STRING:
                    return new StringToken(tk, pos, endPos, reader.chars(), comments);
                case NUMERIC:
                    return new NumericToken(tk, pos, endPos, reader.chars(), 10, comments);
                default:
                    throw new AssertionError();
            }
        } finally {
            if (scannerDebug) {
                System.out.println("nextToken(" + pos
                        + "," + endPos + ")=|" +
                        new String(reader.getRawCharacters(pos, endPos))
                        + "|");
            }
        }
    }


    /**
     * Return the position where a lexical error occurred;
     */
    public int errPos() {
        return errPos;
    }

    /**
     * Set the position where a lexical error occurred;
     */
    public void errPos(int pos) {
        errPos = pos;
    }

    /**
     * Called when a complete whitespace run has been scanned. pos and endPos
     * will mark the whitespace boundary.
     */
    protected void processWhiteSpace(int pos, int endPos) {
        if (scannerDebug)
            System.out.println("processWhitespace(" + pos
                    + "," + endPos + ")=|" +
                    new String(reader.getRawCharacters(pos, endPos))
                    + "|");
    }

    /**
     * Called when a line terminator has been processed.
     */
    protected void processLineTerminator(int pos, int endPos) {
        if (scannerDebug)
            System.out.println("processTerminator(" + pos
                    + "," + endPos + ")=|" +
                    new String(reader.getRawCharacters(pos, endPos))
                    + "|");
    }

    /**
     * Build a map for translating between line numbers and
     * positions in the input.
     *
     * @return a LineMap
     */
    public Position.LineMap getLineMap() {
        return Position.makeLineMap(reader.getRawCharacters(), reader.buflen, false);
    }
}
