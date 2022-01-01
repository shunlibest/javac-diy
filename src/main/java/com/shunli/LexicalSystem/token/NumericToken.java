package com.shunli.LexicalSystem.token;

import java.util.List;

public final class NumericToken extends StringToken {
    /**
     * The 'radix' value of this token
     */
    public final int radix;

    public NumericToken(TokenKind kind, int pos, int endPos, String stringVal, int radix, List<Tokens.Comment> comments) {
        super(kind, pos, endPos, stringVal, comments);
        this.radix = radix;
    }

    protected void checkKind() {
        if (kind.tag != TokenKind.Tag.NUMERIC) {
            throw new AssertionError("Bad token kind - expected " + TokenKind.Tag.NUMERIC);
        }
    }

    @Override
    public int radix() {
        return radix;
    }
}
