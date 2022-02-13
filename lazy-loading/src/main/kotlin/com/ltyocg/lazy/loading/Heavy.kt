package com.ltyocg.lazy.loading

import org.slf4j.LoggerFactory

class Heavy {
    private val log = LoggerFactory.getLogger(javaClass)

    init {
        log.info("Creating Heavy ...")
        Thread.sleep(1000)
        log.info("... Heavy created")
    }
}