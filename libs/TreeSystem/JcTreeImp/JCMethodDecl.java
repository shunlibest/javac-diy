package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.LexicalSystem.name.Name;
import com.shunli.TreeSystem.JCTree;

import java.util.List;

/**
 * 方法: 每个方法都由此实现, 包括抽象和非抽象方法
 */
public class JCMethodDecl extends JCTree {
    /**
     * method modifiers
     */
    public JCModifiers mods;

    //方法名称
    public Name name;
    /**
     * type of method return value
     */
    public JCExpression restype;
    /**
     * type parameters
     */
    public List<JCTypeParameter> typarams;
    /**
     * receiver parameter
     */
    public JCVariableDecl recvparam;
    /**
     * value parameters
     */
    public List<JCVariableDecl> params;
    /**
     * exceptions thrown by this method
     */
    public List<JCExpression> thrown;
    /**
     * statements in the method
     */
    public JCBlock body;
    /**
     * default value, for annotation types
     */
    public JCExpression defaultValue;
    /**
     * method symbol
     */
    public MethodSymbol sym;

    protected JCMethodDecl(JCModifiers mods,
                           Name name,
                           JCExpression restype,
                           List<JCTypeParameter> typarams,
                           JCVariableDecl recvparam,
                           List<JCVariableDecl> params,
                           List<JCExpression> thrown,
                           JCBlock body,
                           JCExpression defaultValue,
                           MethodSymbol sym) {
        this.mods = mods;
        this.name = name;
        this.restype = restype;
        this.typarams = typarams;
        this.params = params;
        this.recvparam = recvparam;
        // TODO: do something special if the given type is null?
        // receiver != null ? receiver : List.<JCTypeAnnotation>nil());
        this.thrown = thrown;
        this.body = body;
        this.defaultValue = defaultValue;
        this.sym = sym;
    }

    @Override
    public void accept(Visitor v) {
        v.visitMethodDef(this);
    }

    public Kind getKind() {
        return Kind.METHOD;
    }

    public JCModifiers getModifiers() {
        return mods;
    }

    public Name getName() {
        return name;
    }

    public JCTree getReturnType() {
        return restype;
    }

    public List<JCTypeParameter> getTypeParameters() {
        return typarams;
    }

    public List<JCVariableDecl> getParameters() {
        return params;
    }

    public JCVariableDecl getReceiverParameter() {
        return recvparam;
    }

    public List<JCExpression> getThrows() {
        return thrown;
    }

    public JCBlock getBody() {
        return body;
    }

    public JCTree getDefaultValue() { // for annotation types
        return defaultValue;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitMethod(this, d);
    }

    @Override
    public Tag getTag() {
        return METHODDEF;
    }
}
