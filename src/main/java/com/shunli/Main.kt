package com.shunli

import com.shunli.FileSystem.RegularFileObject
import com.shunli.LexicalSystem.JavaTokenizer
import com.shunli.LexicalSystem.ReaderHelper
import java.io.File


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        //S0: 文件系统
//        val inputFile = this.javaClass.classLoader.getResource("javaFiles/HelloWorld.txt")!!.file
        val inputFile = this.javaClass.classLoader.getResource("javaFiles/BubbleSort.txt")!!.file
        val regularFileObject = RegularFileObject(File(inputFile))

        val readerHelper = ReaderHelper(inputFile)


        //S1:词法分析系统
        val javaTokenizer = JavaTokenizer(readerHelper)

        do {
            val readToken = javaTokenizer.readToken()
            println(readToken?.text ?: "-----------\nfinish read all tokens")
        } while (readToken != null)
    }


//        val lexer = scannerFactory.newScanner(charContent, false)
}