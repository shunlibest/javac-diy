package com.shunli

import com.shunli.FileSystem.RegularFileObject
import com.shunli.LexicalSystem.Lexer
import com.shunli.LexicalSystem.ScannerFactory
import java.io.File


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        //S1: 文件系统
//        val regularFileObject =
//            RegularFileObject(File("../resources/javaFiles/HelloWorld.txt"))

        val inputFile = this.javaClass.classLoader.getResource("javaFiles/HelloWorld.txt")!!.file
        val regularFileObject = RegularFileObject(File(inputFile))

        val charContent = regularFileObject.getCharContent(false)
        //S2:词法分析系统
        val scannerFactory: ScannerFactory = ScannerFactory.instance()
        val lexer: Lexer = scannerFactory.newScanner(charContent, false)
        repeat(100) {
            lexer.nextToken()
        }
    }
}