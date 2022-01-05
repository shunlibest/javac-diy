package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.TreeSystem.JCTree;
import com.shunli.TreeSystem.TreeKind;
import com.shunli.TreeSystem.TreeTag;
import com.sun.tools.javac.code.Flags;

import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.Set;

/**
 * 表示修饰符, 如:public, abstract, native等
 */
public class JCModifiers extends JCTree {
    //修饰符
    public long flags;
    public List<JCAnnotation> annotations;

    protected JCModifiers(long flags, List<JCAnnotation> annotations) {
        this.flags = flags;
        this.annotations = annotations;
    }

    @Override
    public void accept(Visitor v) {
        v.visitModifiers(this);
    }

    public TreeKind getKind() {
        return TreeKind.MODIFIERS;
    }

    public Set<Modifier> getFlags() {
        return Flags.asModifierSet(flags);
    }

    public List<JCAnnotation> getAnnotations() {
        return annotations;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitModifiers(this, d);
    }

    @Override
    public TreeTag getTag() {
        return TreeTag.MODIFIERS;
    }
}
