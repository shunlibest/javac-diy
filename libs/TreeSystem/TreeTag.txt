package com.shunli.TreeSystem;

/**
 * 树的种类
 */
public enum TreeTag {
    /**
     * For methods that return an invalid tag if a given condition is not met
     */
    NO_TAG,

    /**
     * Toplevel nodes, of type TopLevel, representing entire source files.
     */
    TOPLEVEL,

    /**
     * Import clauses, of type Import.
     */
    IMPORT,

    /**
     * Class definitions, of type ClassDef.
     */
    CLASSDEF,

    /**
     * Method definitions, of type MethodDef.
     */
    METHODDEF,

    /**
     * Variable definitions, of type VarDef.
     */
    VARDEF,

    /**
     * The no-op statement ";", of type Skip
     */
    SKIP,

    /**
     * Blocks, of type Block.
     */
    BLOCK,

    /**
     * Do-while loops, of type DoLoop.
     */
    DOLOOP,

    /**
     * While-loops, of type WhileLoop.
     */
    WHILELOOP,

    /**
     * For-loops, of type ForLoop.
     */
    FORLOOP,

    /**
     * Foreach-loops, of type ForeachLoop.
     */
    FOREACHLOOP,

    /**
     * Labelled statements, of type Labelled.
     */
    LABELLED,

    /**
     * Switch statements, of type Switch.
     */
    SWITCH,

    /**
     * Case parts in switch statements, of type Case.
     */
    CASE,

    /**
     * Synchronized statements, of type Synchonized.
     */
    SYNCHRONIZED,

    /**
     * Try statements, of type Try.
     */
    TRY,

    /**
     * Catch clauses in try statements, of type Catch.
     */
    CATCH,

    /**
     * Conditional expressions, of type Conditional.
     */
    CONDEXPR,

    /**
     * Conditional statements, of type If.
     */
    IF,

    /**
     * Expression statements, of type Exec.
     */
    EXEC,

    /**
     * Break statements, of type Break.
     */
    BREAK,

    /**
     * Continue statements, of type Continue.
     */
    CONTINUE,

    /**
     * Return statements, of type Return.
     */
    RETURN,

    /**
     * Throw statements, of type Throw.
     */
    THROW,

    /**
     * Assert statements, of type Assert.
     */
    ASSERT,

    /**
     * Method invocation expressions, of type Apply.
     */
    APPLY,

    /**
     * Class instance creation expressions, of type NewClass.
     */
    NEWCLASS,

    /**
     * Array creation expressions, of type NewArray.
     */
    NEWARRAY,

    /**
     * Lambda expression, of type Lambda.
     */
    LAMBDA,

    /**
     * Parenthesized subexpressions, of type Parens.
     */
    PARENS,

    /**
     * Assignment expressions, of type Assign.
     */
    ASSIGN,

    /**
     * Type cast expressions, of type TypeCast.
     */
    TYPECAST,

    /**
     * Type test expressions, of type TypeTest.
     */
    TYPETEST,

    /**
     * Indexed array expressions, of type Indexed.
     */
    INDEXED,

    /**
     * Selections, of type Select.
     */
    SELECT,

    /**
     * Member references, of type Reference.
     */
    REFERENCE,

    /**
     * Simple identifiers, of type Ident.
     */
    IDENT,

    /**
     * Literals, of type Literal.
     */
    LITERAL,

    /**
     * Basic type identifiers, of type TypeIdent.
     */
    TYPEIDENT,

    /**
     * Array types, of type TypeArray.
     */
    TYPEARRAY,

    /**
     * Parameterized types, of type TypeApply.
     */
    TYPEAPPLY,

    /**
     * Union types, of type TypeUnion.
     */
    TYPEUNION,

    /**
     * Intersection types, of type TypeIntersection.
     */
    TYPEINTERSECTION,

    /**
     * Formal type parameters, of type TypeParameter.
     */
    TYPEPARAMETER,

    /**
     * Type argument.
     */
    WILDCARD,

    /**
     * Bound kind: extends, super, exact, or unbound
     */
    TYPEBOUNDKIND,

    /**
     * metadata: Annotation.
     */
    ANNOTATION,

    /**
     * metadata: Type annotation.
     */
    TYPE_ANNOTATION,

    /**
     * metadata: Modifiers
     */
    MODIFIERS,

    /**
     * An annotated type tree.
     */
    ANNOTATED_TYPE,

    /**
     * Error trees, of type Erroneous.
     */
    ERRONEOUS,

    /**
     * Unary operators, of type Unary.
     */
    POS,                             // +
    NEG,                             // -
    NOT,                             // !
    COMPL,                           // ~
    PREINC,                          // ++ _
    PREDEC,                          // -- _
    POSTINC,                         // _ ++
    POSTDEC,                         // _ --

    /**
     * unary operator for null reference checks, only used internally.
     */
    NULLCHK,

    /**
     * Binary operators, of type Binary.
     */
    OR,                              // ||
    AND,                             // &&
    BITOR,                           // |
    BITXOR,                          // ^
    BITAND,                          // &
    EQ,                              // ==
    NE,                              // !=
    LT,                              // <
    GT,                              // >
    LE,                              // <=
    GE,                              // >=
    SL,                              // <<
    SR,                              // >>
    USR,                             // >>>
    PLUS,                            // +
    MINUS,                           // -
    MUL,                             // *
    DIV,                             // /
    MOD,                             // %

    /**
     * Assignment operators, of type Assignop.
     */
    BITOR_ASG(BITOR),                // |=
    BITXOR_ASG(BITXOR),              // ^=
    BITAND_ASG(BITAND),              // &=

    SL_ASG(SL),                      // <<=
    SR_ASG(SR),                      // >>=
    USR_ASG(USR),                    // >>>=
    PLUS_ASG(PLUS),                  // +=
    MINUS_ASG(MINUS),                // -=
    MUL_ASG(MUL),                    // *=
    DIV_ASG(DIV),                    // /=
    MOD_ASG(MOD),                    // %=

    /**
     * A synthetic let expression, of type LetExpr.
     */
    LETEXPR;                         // ala scheme

    private final Tag noAssignTag;

    private static final int numberOfOperators = MOD.ordinal() - POS.ordinal() + 1;

    private Tag(Tag noAssignTag) {
        this.noAssignTag = noAssignTag;
    }

    private Tag() {
        this(null);
    }

    public static int getNumberOfOperators() {
        return numberOfOperators;
    }

    public Tag noAssignOp() {
        if (noAssignTag != null)
            return noAssignTag;
        throw new AssertionError("noAssignOp() method is not available for non assignment tags");
    }

    public boolean isPostUnaryOp() {
        return (this == POSTINC || this == POSTDEC);
    }

    public boolean isIncOrDecUnaryOp() {
        return (this == PREINC || this == PREDEC || this == POSTINC || this == POSTDEC);
    }

    public boolean isAssignop() {
        return noAssignTag != null;
    }

    public int operatorIndex() {
        return (this.ordinal() - POS.ordinal());
    }
}
