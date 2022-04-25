package com.shunli.TreeSystem.JcTreeImp;

import com.shunli.TreeSystem.DocCommentTable;
import com.shunli.TreeSystem.EndPosTable;
import com.shunli.TreeSystem.JCTree;

import javax.tools.JavaFileObject;
import java.util.List;

/**
 * 一个源文件中的所有内容都保存在JCCompilationUnit结构中。
 * 一个类中, 只有一个编译单元; 包括:
 * PackageDeclaration: 包声明
 * ImportDeclaration:导入声明
 * TypeDeclaration: 类型声明
 */
public class JCCompilationUnit extends JCTree implements CompilationUnitTree {
    public List<JCAnnotation> packageAnnotations;
    /**
     * The tree representing the package clause.
     */
    public JCExpression pid;
    /**
     * All definitions in this file (ClassDef, Import, and Skip)
     */
    public List<JCTree> defs;
    /* The source file name. */
    public JavaFileObject sourcefile;
    /**
     * The package to which this compilation unit belongs.
     */
    public PackageSymbol packge;
    /**
     * A scope for all named imports.
     */
    public ImportScope namedImportScope;
    /**
     * A scope for all import-on-demands.
     */
    public StarImportScope starImportScope;
    /**
     * Line starting positions, defined only if option -g is set.
     */
    public Position.LineMap lineMap = null;
    /**
     * A table that stores all documentation comments indexed by the tree
     * nodes they refer to. defined only if option -s is set.
     */
    public DocCommentTable docComments = null;
    /* An object encapsulating ending positions of source ranges indexed by
     * the tree nodes they belong to. Defined only if option -Xjcov is set. */
    public EndPosTable endPositions = null;

    protected JCCompilationUnit(List<JCAnnotation> packageAnnotations,
                                JCExpression pid,
                                List<JCTree> defs,
                                JavaFileObject sourcefile,
                                PackageSymbol packge,
                                ImportScope namedImportScope,
                                StarImportScope starImportScope) {
        this.packageAnnotations = packageAnnotations;
        this.pid = pid;
        this.defs = defs;
        this.sourcefile = sourcefile;
        this.packge = packge;
        this.namedImportScope = namedImportScope;
        this.starImportScope = starImportScope;
    }

    @Override
    public void accept(Visitor v) {
        v.visitTopLevel(this);
    }

    public Kind getKind() {
        return Kind.COMPILATION_UNIT;
    }

    public List<JCAnnotation> getPackageAnnotations() {
        return packageAnnotations;
    }

    public List<JCImport> getImports() {
        ListBuffer<JCImport> imports = new ListBuffer<JCImport>();
        for (JCTree tree : defs) {
            if (tree.hasTag(IMPORT))
                imports.append((JCImport) tree);
            else if (!tree.hasTag(SKIP))
                break;
        }
        return imports.toList();
    }

    public JCExpression getPackageName() {
        return pid;
    }

    public JavaFileObject getSourceFile() {
        return sourcefile;
    }

    public Position.LineMap getLineMap() {
        return lineMap;
    }

    public List<JCTree> getTypeDecls() {
        List<JCTree> typeDefs;
        for (typeDefs = defs; !typeDefs.isEmpty(); typeDefs = typeDefs.tail)
            if (!typeDefs.head.hasTag(IMPORT))
                break;
        return typeDefs;
    }

    @Override
    public <R, D> R accept(TreeVisitor<R, D> v, D d) {
        return v.visitCompilationUnit(this, d);
    }

    @Override
    public Tag getTag() {
        return TOPLEVEL;
    }
}
