package com.shunli.LexicalSystem.token;

import com.sun.tools.javac.api.Formattable;
import com.sun.tools.javac.util.Filter;
import com.sun.tools.javac.api.Messages;

import java.util.Locale;

/**
 * 枚举了所用到的所有Token类型; Token可选择与名称相关联。
 */
public enum TokenKind implements Formattable, Filter<TokenKind> {

    EOF(),
    ERROR(),
    IDENTIFIER(Tag.NAMED),
    ABSTRACT("abstract"),
    ASSERT("assert", Tag.NAMED),
    BOOLEAN("boolean", Tag.NAMED),
    BREAK("break"),
    BYTE("byte", Tag.NAMED),
    CASE("case"),
    CATCH("catch"),
    CHAR("char", Tag.NAMED),
    CLASS("class"),
    CONST("const"),
    CONTINUE("continue"),
    DEFAULT("default"),
    DO("do"),
    DOUBLE("double", Tag.NAMED),
    ELSE("else"),
    ENUM("enum", Tag.NAMED),
    EXTENDS("extends"),
    FINAL("final"),
    FINALLY("finally"),
    FLOAT("float", Tag.NAMED),
    FOR("for"),
    GOTO("goto"),
    IF("if"),
    IMPLEMENTS("implements"),
    IMPORT("import"),
    INSTANCEOF("instanceof"),
    INT("int", Tag.NAMED),
    INTERFACE("interface"),
    LONG("long", Tag.NAMED),
    NATIVE("native"),
    NEW("new"),
    PACKAGE("package"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public"),
    RETURN("return"),
    SHORT("short", Tag.NAMED),
    STATIC("static"),
    STRICTFP("strictfp"),
    SUPER("super", Tag.NAMED),
    SWITCH("switch"),
    SYNCHRONIZED("synchronized"),
    THIS("this", Tag.NAMED),
    THROW("throw"),
    THROWS("throws"),
    TRANSIENT("transient"),
    TRY("try"),
    VOID("void", Tag.NAMED),
    VOLATILE("volatile"),
    WHILE("while"),
    INTLITERAL(Tag.NUMERIC),
    LONGLITERAL(Tag.NUMERIC),
    FLOATLITERAL(Tag.NUMERIC),
    DOUBLELITERAL(Tag.NUMERIC),
    CHARLITERAL(Tag.NUMERIC),
    STRINGLITERAL(Tag.STRING),
    TRUE("true", Tag.NAMED),
    FALSE("false", Tag.NAMED),
    NULL("null", Tag.NAMED),
    UNDERSCORE("_", Tag.NAMED),
    ARROW("->"),
    COLCOL("::"),
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    LBRACKET("["),
    RBRACKET("]"),
    SEMI(";"),
    COMMA(","),
    DOT("."),
    ELLIPSIS("..."),
    EQ("="),
    GT(">"),
    LT("<"),
    BANG("!"),
    TILDE("~"),
    QUES("?"),
    COLON(":"),
    EQEQ("=="),
    LTEQ("<="),
    GTEQ(">="),
    BANGEQ("!="),
    AMPAMP("&&"),
    BARBAR("||"),
    PLUSPLUS("++"),
    SUBSUB("--"),
    PLUS("+"),
    SUB("-"),
    STAR("*"),
    SLASH("/"),
    AMP("&"),
    BAR("|"),
    CARET("^"),
    PERCENT("%"),
    LTLT("<<"),
    GTGT(">>"),
    GTGTGT(">>>"),
    PLUSEQ("+="),
    SUBEQ("-="),
    STAREQ("*="),
    SLASHEQ("/="),
    AMPEQ("&="),
    BAREQ("|="),
    CARETEQ("^="),
    PERCENTEQ("%="),
    LTLTEQ("<<="),
    GTGTEQ(">>="),
    GTGTGTEQ(">>>="),
    MONKEYS_AT("@"),
    CUSTOM;
//
//    //类型1: 输入流校验的错误
//    EOF(),
//    ERROR(),
//    //类型B:
//
//
//    //B-2:控制流程语句
//    BREAK("break"),
//    CONTINUE("continue"),
//    FOR("for"),
//    IF("if"),
//    CASE("case"),
//    CATCH("catch"),
//    DEFAULT("default"),
//    DO("do"),
//    ELSE("else"),
//    RETURN("return"),
//    SWITCH("switch"),
//    THROW("throw"),
//    THROWS("throws"),
//    TRY("try"),
//    WHILE("while"),
//
//    //B-3: 修饰符
//    ABSTRACT("abstract"),
//    NATIVE("native"),
//    PRIVATE("private"),
//    PROTECTED("protected"),
//    PUBLIC("public"),
//    STATIC("static"),
//    STRICTFP("strictfp"),
//    SYNCHRONIZED("synchronized"),
//    TRANSIENT("transient"),
//    VOLATILE("volatile"),
//    //B-4:保留字
//    CONST("const"),
//    GOTO("goto"),
//
//
//    IDENTIFIER(Tag.NAMED),
//    ASSERT("assert", Tag.NAMED),
//    BOOLEAN("boolean", Tag.NAMED),
//    BYTE("byte", Tag.NAMED),
//    CHAR("char", Tag.NAMED),
//    CLASS("class"),
//
//    DOUBLE("double", Tag.NAMED),
//    ENUM("enum", Tag.NAMED),
//    EXTENDS("extends"),
//    FINAL("final"),
//    FINALLY("finally"),
//    FLOAT("float", Tag.NAMED),
//    IMPLEMENTS("implements"),
//    IMPORT("import"),
//    INSTANCEOF("instanceof"),
//    INT("int", Tag.NAMED),
//    INTERFACE("interface"),
//    LONG("long", Tag.NAMED),
//    NEW("new"),
//    PACKAGE("package"),
//
//
//    SHORT("short", Tag.NAMED),
//    SUPER("super", Tag.NAMED),
//    THIS("this", Tag.NAMED),
//    VOID("void", Tag.NAMED),
//    INTLITERAL(Tag.NUMERIC),
//    LONGLITERAL(Tag.NUMERIC),
//    FLOATLITERAL(Tag.NUMERIC),
//    DOUBLELITERAL(Tag.NUMERIC),
//    CHARLITERAL(Tag.NUMERIC),
//    STRINGLITERAL(Tag.STRING),
//    TRUE("true", Tag.NAMED),
//    FALSE("false", Tag.NAMED),
//    NULL("null", Tag.NAMED),
//    UNDERSCORE("_", Tag.NAMED),
//
//    //类型四: 各种标识符号
//    ARROW("->"),
//    COLCOL("::"),
//    LPAREN("("),
//    RPAREN(")"),
//    LBRACE("{"),
//    RBRACE("}"),
//    LBRACKET("["),
//    RBRACKET("]"),
//    SEMI(";"),
//    COMMA(","),
//    DOT("."),
//    ELLIPSIS("..."),
//    EQ("="),
//    GT(">"),
//    LT("<"),
//    BANG("!"),
//    TILDE("~"),
//    QUES("?"),
//    COLON(":"),
//    EQEQ("=="),
//    LTEQ("<="),
//    GTEQ(">="),
//    BANGEQ("!="),
//    AMPAMP("&&"),
//    BARBAR("||"),
//    PLUSPLUS("++"),
//    SUBSUB("--"),
//    PLUS("+"),
//    SUB("-"),
//    STAR("*"),
//    SLASH("/"),
//    AMP("&"),
//    BAR("|"),
//    CARET("^"),
//    PERCENT("%"),
//    LTLT("<<"),
//    GTGT(">>"),
//    GTGTGT(">>>"),
//    PLUSEQ("+="),
//    SUBEQ("-="),
//    STAREQ("*="),
//    SLASHEQ("/="),
//    AMPEQ("&="),
//    BAREQ("|="),
//    CARETEQ("^="),
//    PERCENTEQ("%="),
//    LTLTEQ("<<="),
//    GTGTEQ(">>="),
//    GTGTGTEQ(">>>="),
//    MONKEYS_AT("@"),
//    CUSTOM;

    public final String name;
    public final Tag tag;

    TokenKind() {
        this(null, Tag.DEFAULT);
    }

    TokenKind(String name) {
        this(name, Tag.DEFAULT);
    }

    TokenKind(Tag tag) {
        this(null, tag);
    }

    TokenKind(String name, Tag tag) {
        this.name = name;
        this.tag = tag;
    }

    public String toString() {
        switch (this) {
            case IDENTIFIER:
                return "token.identifier";
            case CHARLITERAL:
                return "token.character";
            case STRINGLITERAL:
                return "token.string";
            case INTLITERAL:
                return "token.integer";
            case LONGLITERAL:
                return "token.long-integer";
            case FLOATLITERAL:
                return "token.float";
            case DOUBLELITERAL:
                return "token.double";
            case ERROR:
                return "token.bad-symbol";
            case EOF:
                return "token.end-of-input";
            case DOT:
            case COMMA:
            case SEMI:
            case LPAREN:
            case RPAREN:
            case LBRACKET:
            case RBRACKET:
            case LBRACE:
            case RBRACE:
                return "'" + name + "'";
            default:
                return name;
        }
    }

    @Override
    public String toString(Locale locale, Messages messages) {
        return name != null ? toString() : messages.getLocalizedString(locale, "compiler.misc." + toString());
    }

    public String getKind() {
        return "Token";
    }

    @Override
    public boolean accepts(TokenKind that) {
        return this == that;
    }

    //标签常量
    public enum Tag {
        DEFAULT,
        NAMED,
        STRING,
        NUMERIC;
    }
}
