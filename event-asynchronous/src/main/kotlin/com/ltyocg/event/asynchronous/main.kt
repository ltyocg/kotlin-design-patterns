package com.ltyocg.event.asynchronous

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    with(EventManager()) {
        val asyncEventId = createAsync(60)
        log.info("Async Event [{}] has been created.", asyncEventId)
        start(asyncEventId)
        log.info("Async Event [{}] has been started.", asyncEventId)
        val syncEventId = create(60)
        log.info("Sync Event [{}] has been created.", syncEventId)
        start(syncEventId)
        log.info("Sync Event [{}] has been started.", syncEventId)
        status(asyncEventId)
        status(syncEventId)
        cancel(asyncEventId)
        log.info("Async Event [{}] has been stopped.", asyncEventId)
        cancel(syncEventId)
        log.info("Sync Event [{}] has been stopped.", syncEventId)
    }
}