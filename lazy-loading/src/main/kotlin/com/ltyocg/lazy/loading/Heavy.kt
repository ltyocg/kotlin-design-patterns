package com.ltyocg.lazy.loading

import org.slf4j.LoggerFactory

class Heavy {
    private val log = LoggerFactory.getLogger(this::class.java)

    init {
        log.info("Creating Heavy ...")
        Thread.sleep(1000)
        log.info("... Heavy created")
    }
}