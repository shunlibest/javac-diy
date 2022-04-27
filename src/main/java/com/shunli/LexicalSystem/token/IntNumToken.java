package com.shunli.LexicalSystem.token;

public class IntNumToken extends Token {
    private int value;

    public IntNumToken(int line, int v) {
        super(line);
        value = v;
    }

    public boolean isNumber() {
        return true;
    }

    public String getText() {
        return Integer.toString(value);
    }

    public float getNumber() {
        return value;
    }
}