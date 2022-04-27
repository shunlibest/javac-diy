package com.shunli.LexicalSystem.token;
import java.util.Locale;


public enum TokenKind{

    //类型A: 各种标识符号
    ARROW("->"), COLCOL("::"), LPAREN("("), RPAREN(")"), LBRACE("{"),
    RBRACE("}"), LBRACKET("["), RBRACKET("]"), SEMI(";"), COMMA(","),
    DOT("."), ELLIPSIS("..."), EQ("="), GT(">"), LT("<"),
    BANG("!"), TILDE("~"), QUES("?"), COLON(":"), EQEQ("=="),
    LTEQ("<="), GTEQ(">="), BANGEQ("!="), AMPAMP("&&"), BARBAR("||"),
    PLUSPLUS("++"), SUBSUB("--"), PLUS("+"), SUB("-"), STAR("*"),
    SLASH("/"), AMP("&"), BAR("|"), CARET("^"), PERCENT("%"),
    LTLT("<<"), GTGT(">>"), GTGTGT(">>>"), PLUSEQ("+="), SUBEQ("-="),
    STAREQ("*="), SLASHEQ("/="), AMPEQ("&="), BAREQ("|="), CARETEQ("^="),
    PERCENTEQ("%="), LTLTEQ("<<="), GTGTEQ(">>="), GTGTGTEQ(">>>="), MONKEYS_AT("@"),

    //类型1: 输入流校验的错误
    EOF(),
    ERROR(),
    //类型B:


    //B-2:控制流程语句
    BREAK("break"), CONTINUE("continue"), FOR("for"), IF("if"),
    CASE("case"), CATCH("catch"), DEFAULT("default"), DO("do"),
    ELSE("else"), RETURN("return"), SWITCH("switch"), THROW("throw"),
    THROWS("throws"), TRY("try"), WHILE("while"),

    //B-3: 修饰符
    ABSTRACT("abstract"), NATIVE("native"), PRIVATE("private"), PROTECTED("protected"),
    PUBLIC("public"), STATIC("static"), STRICTFP("strictfp"), SYNCHRONIZED("synchronized"),
    TRANSIENT("transient"), VOLATILE("volatile"),
    //B-4:保留字
    CONST("const"), GOTO("goto"),


    IDENTIFIER(Tag.NAMED),
    ASSERT("assert", Tag.NAMED),
    BOOLEAN("boolean", Tag.NAMED),
    BYTE("byte", Tag.NAMED),
    CHAR("char", Tag.NAMED),
    CLASS("class"),

    DOUBLE("double", Tag.NAMED),
    ENUM("enum", Tag.NAMED),
    EXTENDS("extends"),
    FINAL("final"),
    FINALLY("finally"),
    FLOAT("float", Tag.NAMED),
    IMPLEMENTS("implements"),
    IMPORT("import"),
    INSTANCEOF("instanceof"),
    INT("int", Tag.NAMED),
    INTERFACE("interface"),
    LONG("long", Tag.NAMED),
    NEW("new"),
    PACKAGE("package"),


    SHORT("short", Tag.NAMED),
    SUPER("super", Tag.NAMED),
    THIS("this", Tag.NAMED),
    VOID("void", Tag.NAMED),
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


    CUSTOM;

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


    public String getKind() {
        return "Token";
    }


    //标签常量
    public enum Tag {
        DEFAULT,
        NAMED,
        STRING,
        NUMERIC;
    }
}
