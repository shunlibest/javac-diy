package com.shunli.LexicalSystem;

import org.jetbrains.annotations.NotNull;

public class LexicalAnalyzerUtil {

    // 判断是否为字母
    public static boolean isLetter(char c) {
        return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }

    public static boolean isLetterOrNum(char c) {
        return isLetter(c) || isNum(c);
    }


    public static boolean isNum(char c) {
        return (c >= '0') && (c <= '9');
    }


    // 判断是否为关键字，若是则返回关键字种别码
    public static int isKeyID(String str) {
        String keystr[] = {"char", "short", "int", "long", "float", "double", "final", "static", "if", "else", "while", "do", "for", "break", "continue", "void", "return"};
        for (int i = 0; i < keystr.length; i++) {
            if (str.equals(keystr[i])) {
                return i + 1;
            }
        }
        return 0;
    }


    // 判断是否为常量（整数、小数、浮点数）
    public static boolean isNum(@NotNull String str) {
        int dot = 0; // .的个数
        int notNum = 0; // 不是数字的个数
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                notNum++;
                if (notNum > dot + 1) {
                    System.out.println("该常量" + str + "的词法不正确");
                    return false;
                } else if (str.charAt(i) == '.') {
                    dot++;
                    if (dot > 1) {
                        System.out.println("该常量" + str + "的词法不正确");
                        return false;
                    }
                } else if ((str.charAt(i - 1) >= '0' && str.charAt(i - 1) <= '9') && (str.charAt(i) == 'E') && (i == str.length() - 1 || (str.charAt(i + 1) >= '0' && str.charAt(i + 1) <= '9'))) {
                    continue;
                } else {
                    System.out.println("该常量" + str + "的词法不正确");
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Return true if reader.ch can be part of an operator.
     */
    public static boolean isSpecialChar(char ch) {
        char[] specialChar = new char[]{'!', '%'};
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

    public static float stringToFloatNum(String string) {
        return Float.parseFloat(string);
    }

    public static int stringToNum(String string) {
        return Integer.parseInt(string);
    }
}
