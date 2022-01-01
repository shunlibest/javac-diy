package com.shunli.LexicalSystem.token;


import java.util.List;

public class StringToken extends Token {

    public final String stringVal;

    public StringToken(TokenKind kind, int pos, int endPos, String stringVal, List<Tokens.Comment> comments) {
        super(kind, pos, endPos, comments);
        this.stringVal = stringVal;
    }

    protected void checkKind() {
        if (kind.tag != TokenKind.Tag.STRING) {
            throw new AssertionError("Bad token kind - expected " + TokenKind.Tag.STRING);
        }
    }

    @Override
    public String stringVal() {
        return stringVal;
    }
}