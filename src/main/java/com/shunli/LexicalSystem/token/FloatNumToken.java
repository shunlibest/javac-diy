package com.shunli.LexicalSystem.token;

public class FloatNumToken extends Token {
    private float value;

    public FloatNumToken(int line, float v) {
        super(line);
        value = v;
    }

    public boolean isNumber() {
        return true;
    }

    public String getText() {
        return Float.toString(value);
    }

    public float getNumber() {
        return value;
    }
}