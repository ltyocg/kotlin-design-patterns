package com.ltyocg.activeobject

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

abstract class ActiveCreature
internal constructor(val name: String) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val coroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val coroutineScope = CoroutineScope(coroutineDispatcher)
    var status = 0
        private set
    private val onCompletionHandler: CompletionHandler = {
        if (it != null && status != 0) log.error("Thread was interrupted. --> {}", it.localizedMessage)
    }

    fun eat() {
        coroutineScope.launch {
            log.info("{} is eating!", name)
            log.info("{} has finished eating!", name)
        }.invokeOnCompletion(onCompletionHandler)
    }

    fun roam() {
        coroutineScope.launch {
            log.info("{} has started to roam in the wastelands.", name)
        }.invokeOnCompletion(onCompletionHandler)
    }

    fun kill(status: Int = 0) {
        this.status = status
        coroutineScope.cancel()
        coroutineDispatcher.close()
    }
}