package com.ltyocg.execute.around

fun main() {
    SimpleFileWriter("testfile.txt") {
        it.write("Hello")
        it.append(" ")
        it.append("there!")
    }
}