package com.ltyocg.execute.around

import org.slf4j.LoggerFactory
import java.io.FileWriter

class SimpleFileWriter(filename: String, action: FileWriterAction) {
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        log.info("Opening the file")
        FileWriter(filename).use {
            log.info("Executing the action")
            action.writeFile(it)
            log.info("Closing the file")
        }
    }
}