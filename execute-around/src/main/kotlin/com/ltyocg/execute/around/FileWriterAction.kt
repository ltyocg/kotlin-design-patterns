package com.ltyocg.execute.around

import java.io.FileWriter

fun interface FileWriterAction {
    fun writeFile(writer: FileWriter)
}