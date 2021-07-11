package com.ltyocg.activeobject

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors

abstract class ActiveCreature internal constructor(val name: String) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val context = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    private val coroutineScope = CoroutineScope(context)

    fun eat() {
        coroutineScope.launch {
            log.info("{} is eating!", name)
            log.info("{} has finished eating!", name)
        }
    }

    fun roam() {
        coroutineScope.launch {
            log.info("{} has started to roam in the wastelands.", name)
        }
    }

    fun kill() {
        context.close()
    }
}