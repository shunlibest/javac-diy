package com.shunli.LexicalSystem.token;

public class SpecialCharToken extends Token {
    private String ch;

    public SpecialCharToken(int line, char ch) {
        super(line);
        this.ch = "" + ch;
    }

    public SpecialCharToken(int line, String string) {
        super(line);
        this.ch = string;
    }

    public boolean isString() {
        return true;
    }

    public String getText() {
        return ch;
    }
}
