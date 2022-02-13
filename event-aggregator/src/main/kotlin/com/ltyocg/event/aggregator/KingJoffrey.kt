package com.ltyocg.event.aggregator

import org.slf4j.LoggerFactory

class KingJoffrey : EventObserver {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun onEvent(e: Event?) {
        log.info("Received event from the King's Hand: {}", e)
    }
}