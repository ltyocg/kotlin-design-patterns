package com.ltyocg.ambassador

import org.slf4j.LoggerFactory

class Client {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val serviceAmbassador = ServiceAmbassador()

    fun useService(value: Int): Long =
        serviceAmbassador.doRemoteFunction(value)
            .also { log.info("Service result: {}", it) }
}