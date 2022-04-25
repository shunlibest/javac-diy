package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.TreeSystem.JCTree;

import java.util.List;

/**
 * 类型声明, 包括各种接口, 类, 枚举等
 */
public class JCClassDecl extends JCTree.JCStatement {
    /**
     * the modifiers
     */
    public JCModifiers mods;
    /**
     * the name of the class
     */
    public Name name;
    /**
     * formal class parameters
     */
    public List<JCTypeParameter> typarams;
    /**
     * the classes this class extends
     */
    public JCExpression extending;
    /**
     * the interfaces implemented by this class
     */
    public List<JCExpression> implementing;
    /**
     * all variables and methods defined in this class
     */
    public List<JCTree> defs;
    /**
     * the symbol
     */
    public ClassSymbol sym;

    protected JCClassDecl(JCModifiers mods,
                          Name name,
                          List<JCTypeParameter> typarams,
                          JCExpression extending,
                          List<JCExpression> implementing,
                          List<JCTree> defs,
                          ClassSymbol sym) {
        this.mods = mods;
        this.name = name;
        this.typarams = typarams;
        this.extending = extending;
        this.implementing = implementing;
        this.defs = defs;
        this.sym = sym;
    }

    @Override
    public void accept(Visitor v) {
        v.visitClassDef(this);
    }

    public Kind getKind() {
        if ((mods.flags & Flags.ANNOTATION) != 0)
            return Kind.ANNOTATION_TYPE;
        else if ((mods.flags & Flags.INTERFACE) != 0)
            return Kind.INTERFACE;
        else if ((mods.flags & Flags.ENUM) != 0)
            return Kind.ENUM;
        else
            return Kind.CLASS;
    }

    public JCModifiers getModifiers() {
        return mods;
    }

    public Name getSimpleName() {
        return name;
    }

    public List<JCTypeParameter> getTypeParameters() {
        return typarams;
    }

    public JCExpression getExtendsClause() {
        return extending;
    }

    public List<JCExpression> getImplementsClause() {
        return implementing;
    }

    public List<JCTree> getMembers() {
        return defs;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitClass(this, d);
    }

    @Override
    public Tag getTag() {
        return CLASSDEF;
    }
}
