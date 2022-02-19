package com.ltyocg.execute.around

import org.slf4j.LoggerFactory
import java.io.File
import java.util.*

private val log = LoggerFactory.getLogger("main")
fun main() {
    SimpleFileWriter("testfile.txt") {
        it.write("Gandalf was here")
    }
    Scanner(File("testfile.txt"))
        .useDelimiter(System.getProperty("line.separator"))
        .forEach(log::info)
}