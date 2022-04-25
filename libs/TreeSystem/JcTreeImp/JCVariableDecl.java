package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.TreeSystem.JCTree;

/**
 * A variable definition.
 */
public class JCVariableDecl extends JCTree.JCStatement {
    /**
     * variable modifiers
     */
    public JCModifiers mods;
    /**
     * variable name
     */
    public Name name;

    public JCExpression nameexpr;

    //变量声明的类型
    public JCExpression vartype;
    //变量初始化部分
    public JCExpression init;
    /**
     * symbol
     */
    public VarSymbol sym;

    protected JCVariableDecl(JCModifiers mods,
                             Name name,
                             JCExpression vartype,
                             JCExpression init,
                             VarSymbol sym) {
        this.mods = mods;
        this.name = name;
        this.vartype = vartype;
        this.init = init;
        this.sym = sym;
    }

    protected JCVariableDecl(JCModifiers mods,
                             JCExpression nameexpr,
                             JCExpression vartype) {
        this(mods, null, vartype, null, null);
        this.nameexpr = nameexpr;
        if (nameexpr.hasTag(Tag.IDENT)) {
            this.name = ((JCIdent) nameexpr).name;
        } else {
            // Only other option is qualified name x.y.this;
            this.name = ((JCFieldAccess) nameexpr).name;
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visitVarDef(this);
    }

    public Kind getKind() {
        return Kind.VARIABLE;
    }

    public JCModifiers getModifiers() {
        return mods;
    }

    public Name getName() {
        return name;
    }

    public JCExpression getNameExpression() {
        return nameexpr;
    }

    public JCTree getType() {
        return vartype;
    }

    public JCExpression getInitializer() {
        return init;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitVariable(this, d);
    }

    @Override
    public Tag getTag() {
        return VARDEF;
    }
}
