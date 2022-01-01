package com.shunli

import com.shunli.FileSystem.RegularFileObject
import com.shunli.LexicalSystem.Lexer
import com.shunli.LexicalSystem.ScannerFactory
import java.io.File

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        //S1: 文件系统
        val regularFileObject =
            RegularFileObject(File("/Users/shunli/Documents/codeJava/metting/src/main/java/com/shunli/HelloWorld2.java"))

        val charContent = regularFileObject.getCharContent(false)
        //S2:词法分析系统
        val scannerFactory: ScannerFactory  = ScannerFactory.instance()
        val lexer: Lexer = scannerFactory.newScanner(charContent, false)
//        JavacParser(this, lexer, keepDocComments, keepLineMap, keepEndPos)

        repeat(100){
            lexer.nextToken()

        }
//        while(true){
//        }
    }


    private val javaFile = """
        package com.shunli;

        public final class HelloWorld2  {
            private double hans = 123.4;
            public static void main(String[] args) {
                System.out.println("hello world");
            }
        }        
    """.trimIndent()
}