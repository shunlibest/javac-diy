/*
 * Copyright (c) 1999, 2013, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.shunli.LexicalSystem.name;

/**
 *
 */
public class Names {
    private static Names instance = null;

    public static Names instance() {
        if (instance == null) {
            instance = new Names();
        }
        return instance;
    }

    public final Table table = createTable();

    // 运算符和标点符号
    public final Name asterisk = fromString("*");
    public final Name comma = fromString(",");
    public final Name empty = fromString("");
    public final Name hyphen = fromString("-");
    public final Name one = fromString("1");
    public final Name period = fromString(".");
    public final Name semicolon = fromString(";");
    public final Name slash = fromString("/");
    public final Name slashequals = fromString("/=");

    // 关键字
    public final Name _class = fromString("class");
    public final Name _default = fromString("default");
    public final Name _super = fromString("super");
    public final Name _this = fromString("this");

    // 变量和方法名
    public final Name _name = fromString("name");
    public final Name addSuppressed = fromString("addSuppressed");
    public final Name any = fromString("<any>");
    public final Name append = fromString("append");
    public final Name clinit = fromString("<clinit>");
    public final Name clone = fromString("clone");
    public final Name close = fromString("close");
    public final Name compareTo = fromString("compareTo");
    public final Name deserializeLambda = fromString("$deserializeLambda$");
    public final Name desiredAssertionStatus = fromString("desiredAssertionStatus");
    public final Name equals = fromString("equals");
    public final Name error = fromString("<error>");
    public final Name family = fromString("family");
    public final Name finalize = fromString("finalize");
    public final Name forName = fromString("forName");
    public final Name getClass = fromString("getClass");
    public final Name getClassLoader = fromString("getClassLoader");
    public final Name getComponentType = fromString("getComponentType");
    public final Name getDeclaringClass = fromString("getDeclaringClass");
    public final Name getMessage = fromString("getMessage");
    public final Name hasNext = fromString("hasNext");
    public final Name hashCode = fromString("hashCode");
    public final Name init = fromString("<init>");
    public final Name initCause = fromString("initCause");
    public final Name iterator = fromString("iterator");
    public final Name length = fromString("length");
    public final Name next = fromString("next");
    public final Name ordinal = fromString("ordinal");
    public final Name serialVersionUID = fromString("serialVersionUID");
    public final Name toString = fromString("toString");
    public final Name value = fromString("value");
    public final Name valueOf = fromString("valueOf");
    public final Name values = fromString("values");

    // 类名
    public final Name java_io_Serializable = fromString("java.io.Serializable");
    public final Name java_lang_AutoCloseable = fromString("java.lang.AutoCloseable");
    public final Name java_lang_Class = fromString("java.lang.Class");
    public final Name java_lang_Cloneable = fromString("java.lang.Cloneable");
    public final Name java_lang_Enum = fromString("java.lang.Enum");
    public final Name java_lang_Object = fromString("java.lang.Object");
    public final Name java_lang_invoke_MethodHandle = fromString("java.lang.invoke.MethodHandle");

    // 内置类的名称
    public final Name Array = fromString("Array");
    public final Name Bound = fromString("Bound");
    public final Name Method = fromString("Method");
    // 包名
    public final Name java_lang = fromString("java.lang");

    // 属性名
    public final Name Annotation = fromString("Annotation");
    public final Name AnnotationDefault = fromString("AnnotationDefault");
    public final Name BootstrapMethods = fromString("BootstrapMethods");
    public final Name Bridge = fromString("Bridge");
    public final Name CharacterRangeTable = fromString("CharacterRangeTable");
    public final Name Code = fromString("Code");
    public final Name CompilationID = fromString("CompilationID");
    public final Name ConstantValue = fromString("ConstantValue");
    public final Name Deprecated = fromString("Deprecated");
    public final Name EnclosingMethod = fromString("EnclosingMethod");
    public final Name Enum = fromString("Enum");
    public final Name Exceptions = fromString("Exceptions");
    public final Name InnerClasses = fromString("InnerClasses");
    public final Name LineNumberTable = fromString("LineNumberTable");
    public final Name LocalVariableTable = fromString("LocalVariableTable");
    public final Name LocalVariableTypeTable = fromString("LocalVariableTypeTable");
    public final Name MethodParameters = fromString("MethodParameters");
    public final Name RuntimeInvisibleAnnotations = fromString("RuntimeInvisibleAnnotations");
    public final Name RuntimeInvisibleParameterAnnotations = fromString("RuntimeInvisibleParameterAnnotations");
    public final Name RuntimeInvisibleTypeAnnotations = fromString("RuntimeInvisibleTypeAnnotations");
    public final Name RuntimeVisibleAnnotations = fromString("RuntimeVisibleAnnotations");
    public final Name RuntimeVisibleParameterAnnotations = fromString("RuntimeVisibleParameterAnnotations");
    public final Name RuntimeVisibleTypeAnnotations = fromString("RuntimeVisibleTypeAnnotations");
    public final Name Signature = fromString("Signature");
    public final Name SourceFile = fromString("SourceFile");
    public final Name SourceID = fromString("SourceID");
    public final Name StackMap = fromString("StackMap");
    public final Name StackMapTable = fromString("StackMapTable");
    public final Name Synthetic = fromString("Synthetic");
    public final Name Value = fromString("Value");
    public final Name Varargs = fromString("Varargs");


    // java.lang.annotation.ElementType 的成员
    public final Name ANNOTATION_TYPE = fromString("ANNOTATION_TYPE");
    public final Name CONSTRUCTOR = fromString("CONSTRUCTOR");
    public final Name FIELD = fromString("FIELD");
    public final Name LOCAL_VARIABLE = fromString("LOCAL_VARIABLE");
    public final Name METHOD = fromString("METHOD");
    public final Name PACKAGE = fromString("PACKAGE");
    public final Name PARAMETER = fromString("PARAMETER");
    public final Name TYPE = fromString("TYPE");
    public final Name TYPE_PARAMETER = fromString("TYPE_PARAMETER");
    public final Name TYPE_USE = fromString("TYPE_USE");

    // members of java.lang.annotation.RetentionPolicy
    public final Name CLASS = fromString("CLASS");
    public final Name RUNTIME = fromString("RUNTIME");
    public final Name SOURCE = fromString("SOURCE");

    // 其他标识符
    public final Name T = fromString("T");
    public final Name deprecated = fromString("deprecated");
    public final Name ex = fromString("ex");
    public final Name package_info = fromString("package-info");

    //lambda 相关
    public final Name lambda = fromString("lambda$");
    public final Name metafactory = fromString("metafactory");
    public final Name altMetafactory = fromString("altMetafactory");


    protected Table createTable() {
        return new SharedNameTable(this);
    }

    public void dispose() {
        table.dispose();
    }

    public Name fromChars(char[] cs, int start, int len) {
        return table.fromChars(cs, start, len);
    }

    public Name fromString(String s) {
        return table.fromString(s);
    }

    public Name fromUtf(byte[] cs) {
        return table.fromUtf(cs);
    }

    public Name fromUtf(byte[] cs, int start, int len) {
        return table.fromUtf(cs, start, len);
    }
}
