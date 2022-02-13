package com.ltyocg.eda

import org.slf4j.LoggerFactory

interface Handler<E : Event> {
    fun onEvent(event: E)
}

class UserCreatedEventHandler : Handler<UserCreatedEvent> {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun onEvent(event: UserCreatedEvent) {
        log.info("User '{}' has been Created!", event.user.username)
    }
}

class UserUpdatedEventHandler : Handler<UserUpdatedEvent> {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun onEvent(event: UserUpdatedEvent) {
        log.info("User '{}' has been Updated!", event.user.username)
    }
}