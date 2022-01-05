package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.TreeSystem.JCTree;
import com.shunli.TreeSystem.TreeKind;
import com.shunli.TreeSystem.TreeTag;

/**
 * 导入树, 有以下4中导入语法
 * import com.test;
 * import com.test.*;
 * import static com.test;
 * import static com.test.*;
 */
public class JCImport extends JCTree {
    //是否为静态导入, 也就是有没有static字段
    public boolean staticImport;

    //具体声明导入的包名(包括*)
    public JCTree qualid;

    protected JCImport(JCTree qualid, boolean importStatic) {
        this.qualid = qualid;
        this.staticImport = importStatic;
    }

    @Override
    public void accept(Visitor v) {
        v.visitImport(this);
    }

    public boolean isStatic() {
        return staticImport;
    }

    public JCTree getQualifiedIdentifier() {
        return qualid;
    }

    @Override
    public TreeKind getKind() {
        return TreeKind.IMPORT;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitImport(this, d);
    }

    @Override
    public TreeTag getTag() {
        return TreeTag.IMPORT;
    }
}
