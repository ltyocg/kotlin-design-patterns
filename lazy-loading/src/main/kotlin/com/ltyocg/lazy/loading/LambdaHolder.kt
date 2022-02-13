package com.ltyocg.lazy.loading

import org.slf4j.LoggerFactory

class LambdaHolder {
    private val log = LoggerFactory.getLogger(javaClass)
    private var supplier: () -> Heavy = {
        val heavyInstance = Heavy()
        supplier = { heavyInstance }
        supplier()
    }
    val heavy: Heavy
        get() = supplier()

    init {
        log.info("Java8Holder created")
    }
}