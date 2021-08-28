package com.ltyocg.callback

import org.slf4j.LoggerFactory

abstract class Task {
    fun executeWith(callback: () -> Unit) {
        execute()
        callback()
    }

    abstract fun execute()
}

class SimpleTask : Task() {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun execute() {
        log.info("Perform some important activity and after call the callback method.")
    }
}