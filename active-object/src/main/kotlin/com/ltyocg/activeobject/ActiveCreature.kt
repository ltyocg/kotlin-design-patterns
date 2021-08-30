package com.ltyocg.activeobject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

abstract class ActiveCreature
internal constructor(val name: String) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val coroutineDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val coroutineScope = CoroutineScope(coroutineDispatcher)
    var status = 0
        private set

    fun eat() = invocation {
        log.info("{} is eating!", name)
        log.info("{} has finished eating!", name)
    }

    fun roam() = invocation {
        log.info("{} has started to roam in the wastelands.", name)
    }

    fun kill(status: Int = 0) {
        this.status = status
        coroutineScope.cancel()
        coroutineDispatcher.close()
    }

    private fun invocation(block: () -> Unit) {
        coroutineScope.launch { block() }.invokeOnCompletion {
            if (it != null && status != 0) log.error("Thread was interrupted. --> {}", it.localizedMessage)
        }
    }
}