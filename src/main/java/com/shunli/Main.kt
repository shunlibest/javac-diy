package com.shunli

import com.shunli.FileSystem.RegularFileObject
import java.io.File
import kotlin.jvm.JvmStatic

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        //S1: 文件系统
        val regularFileObject =
            RegularFileObject(File("/Users/shunli/Documents/codeJava/metting/src/main/java/com/shunli/HelloWorld.java"))

        //S2:词法分析系统

    }
}