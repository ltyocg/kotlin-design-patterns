package com.ltyocg.lazy.loading

import org.slf4j.LoggerFactory

class HolderNaive {
    private val log = LoggerFactory.getLogger(javaClass)
    val heavy by lazy(LazyThreadSafetyMode.NONE) { Heavy() }

    init {
        log.info("HolderNaive created")
    }
}