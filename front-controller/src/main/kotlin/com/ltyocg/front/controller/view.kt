package com.ltyocg.front.controller

import org.slf4j.LoggerFactory

interface View {
    fun display()
}

class ArcherView : View {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun display() {
        log.info("Displaying archers")
    }
}

class CatapultView : View {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun display() {
        log.info("Displaying catapults")
    }
}

class ErrorView : View {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun display() {
        log.error("Error 500")
    }
}