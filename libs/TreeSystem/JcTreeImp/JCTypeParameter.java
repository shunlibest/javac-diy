package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.LexicalSystem.name.Name;
import com.shunli.TreeSystem.JCTree;
import com.shunli.TreeSystem.TreeKind;
import com.shunli.TreeSystem.TreeTag;

import java.util.List;

/**
 * 参数类型
 */
public class JCTypeParameter extends JCTree {

    //类型变量的名称
    public Name name;

    //类型变量的上界, 可以有多个
    public List<JCExpression> bounds;

    protected JCTypeParameter(Name name, List<JCExpression> bounds) {
        this.name = name;
        this.bounds = bounds;
    }

    @Override
    public void accept(Visitor v) {
        v.visitTypeParameter(this);
    }

    public TreeKind getKind() {
        return TreeKind.TYPE_PARAMETER;
    }

    public Name getName() {
        return name;
    }

    public List<JCExpression> getBounds() {
        return bounds;
    }

    public List<JCAnnotation> getAnnotations() {
        return annotations;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitTypeParameter(this, d);
    }

    @Override
    public TreeTag getTag() {
        return TreeTag.TYPEPARAMETER;
    }
}
