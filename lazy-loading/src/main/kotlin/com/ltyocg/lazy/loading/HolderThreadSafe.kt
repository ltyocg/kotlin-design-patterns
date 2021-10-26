package com.ltyocg.lazy.loading

import org.slf4j.LoggerFactory

class HolderThreadSafe {
    private val log = LoggerFactory.getLogger(this::class.java)
    val heavy by lazy { Heavy() }

    init {
        log.info("HolderThreadSafe created")
    }
}