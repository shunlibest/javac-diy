package com.shunli.LexicalSystem.token;

import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Name;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class representing a javac token. Each token has several fields
 * that are set by the javac lexer (i.e. start/end position, string value, etc).
 */
public class Token {
    //该Token的类型
    public final TokenKind kind;

    //token开始的位置
    public final int pos;

    //token结束的位置
    public final int endPos;

    /**
     * Comment reader associated with this token
     */
    public final List<Tokens.Comment> comments;

    public Token(TokenKind kind, int pos, int endPos, List<Tokens.Comment> comments) {
        this.kind = kind;
        this.pos = pos;
        this.endPos = endPos;
        this.comments = comments;
        checkKind();
    }

    //把一个token,拆分成两个token
    public Token[] split(Tokens tokens) {
        if (kind.name.length() < 2 || kind.tag != TokenKind.Tag.DEFAULT) {
            throw new AssertionError("Cant split" + kind);
        }

        TokenKind t1 = tokens.lookupKind(kind.name.substring(0, 1));
        TokenKind t2 = tokens.lookupKind(kind.name.substring(1));

        if (t1 == null || t2 == null) {
            throw new AssertionError("Cant split - bad subtokens");
        }
        return new Token[]{
                new Token(t1, pos, pos + t1.name.length(), comments),
                new Token(t2, pos + t1.name.length(), endPos, null)
        };
    }

    protected void checkKind() {
        if (kind.tag != TokenKind.Tag.DEFAULT) {
            throw new AssertionError("Bad token kind - expected " + TokenKind.Tag.STRING);
        }
    }

    public Name name() {
        throw new UnsupportedOperationException();
    }

    public String stringVal() {
        throw new UnsupportedOperationException();
    }

    public int radix() {
        throw new UnsupportedOperationException();
    }

//    /**
//     * Preserve classic semantics - if multiple javadocs are found on the token
//     * the last one is returned
//     */
//    public Tokens.Comment comment(Tokens.Comment.CommentStyle style) {
//        List<Tokens.Comment> comments = getComments(Tokens.Comment.CommentStyle.JAVADOC);
//        return comments.isEmpty() ?
//                null :
//                comments.head;
//    }
//
//    /**
//     * Preserve classic semantics - deprecated should be set if at least one
//     * javadoc comment attached to this token contains the '@deprecated' string
//     */
//    public boolean deprecatedFlag() {
//        for (Tokens.Comment c : getComments(Tokens.Comment.CommentStyle.JAVADOC)) {
//            if (c.isDeprecated()) {
//                return true;
//            }
//        }
//        return false;
//    }

//    private List<Tokens.Comment> getComments(Tokens.Comment.CommentStyle style) {
//        if (comments == null) {
//            return new ArrayList();
//        } else {
//            ListBuffer<Tokens.Comment> buf = new ListBuffer<>();
//            for (Tokens.Comment c : comments) {
//                if (c.getStyle() == style) {
//                    buf.add(c);
//                }
//            }
//            return buf.toList();
//        }
//    }
}