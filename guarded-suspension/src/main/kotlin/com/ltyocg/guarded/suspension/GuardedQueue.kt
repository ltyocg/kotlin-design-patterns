package com.ltyocg.guarded.suspension

import kotlinx.coroutines.channels.Channel
import org.slf4j.LoggerFactory

class GuardedQueue {
    private val log = LoggerFactory.getLogger(javaClass)
    private val sourceList = Channel<Int>(Channel.Factory.BUFFERED)

    @Synchronized
    suspend fun get(): Int {
        @Suppress("EXPERIMENTAL_API_USAGE")
        if (sourceList.isEmpty) log.info("waiting")
        return sourceList.receive().also { log.info("getting") }
    }

    @Synchronized
    suspend fun put(e: Int) {
        log.info("putting")
        sourceList.send(e)
        log.info("notifying")
    }
}