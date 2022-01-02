package com.shunli.LexicalSystem.token;


import com.shunli.LexicalSystem.name.Name;

import java.util.List;

public final class NamedToken extends Token {
    /**
     * The name of this token
     */
    public final Name name;

    public NamedToken(TokenKind kind, int pos, int endPos, Name name, List<Tokens.Comment> comments) {
        super(kind, pos, endPos, comments);
        this.name = name;
    }

    protected void checkKind() {
        if (kind.tag != TokenKind.Tag.NAMED) {
            throw new AssertionError("Bad token kind - expected " + TokenKind.Tag.NAMED);
        }
    }

    @Override
    public Name name() {
        return name;
    }
}