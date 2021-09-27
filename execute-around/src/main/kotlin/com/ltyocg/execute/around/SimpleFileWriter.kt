package com.ltyocg.execute.around

import java.io.FileWriter

class SimpleFileWriter(filename: String, action: FileWriterAction) {
    init {
        FileWriter(filename).use { action.writeFile(it) }
    }
}