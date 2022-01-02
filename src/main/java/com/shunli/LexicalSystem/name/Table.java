package com.shunli.LexicalSystem.name;

/**
 * 所有name构成的HashTable;
 * 可以等效为字符串常量池
 */
public abstract class Table {
    //所有name
    public final Names names;

    Table(Names names) {
        this.names = names;
    }

    //从 cs[start..start+len-1] 中的字符中获取名称。
    public abstract Name fromChars(char[] cs, int start, int len);

    //获取字符串 s 中字符的名称, 相当于把String转为Name
    public Name fromString(String s) {
        char[] cs = s.toCharArray();
        return fromChars(cs, 0, cs.length);
    }

    //从utf8中转为Name
    public Name fromUtf(byte[] cs) {
        return fromUtf(cs, 0, cs.length);
    }

    //Utf8 分割后的字符串转为name
    public abstract Name fromUtf(byte[] cs, int start, int len);

    //释放此表使用的所有资源。
    public abstract void dispose();

    // 获取某一个字符串的hashcode
    protected static int hashValue(byte bytes[], int offset, int length) {
        int h = 0;
        int off = offset;

        for (int i = 0; i < length; i++) {
            h = (h << 5) - h + bytes[off++];
        }
        return h;
    }

    //比较两个字符串是否相同
    protected static boolean equals(byte[] bytes1, int offset1,
                                    byte[] bytes2, int offset2, int length) {
        int i = 0;
        while (i < length && bytes1[offset1 + i] == bytes2[offset2 + i]) {
            i++;
        }
        return i == length;
    }
}