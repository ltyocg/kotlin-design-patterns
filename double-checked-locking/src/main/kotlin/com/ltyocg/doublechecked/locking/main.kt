package com.ltyocg.doublechecked.locking

import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private val log = LoggerFactory.getLogger("main")

fun main() {
    val inventory = Inventory(1000)
    val coroutineDispatcher = Executors.newFixedThreadPool(3).asCoroutineDispatcher()
    runBlocking(coroutineDispatcher) {
        try {
            withTimeout(TimeUnit.SECONDS.toMillis(5)) {
                repeat(3) {
                    launch {
                        while (inventory.addItem(Item())) log.info("Adding another item")
                    }
                }
            }
        } catch (e: TimeoutCancellationException) {
            log.error("Error waiting for ExecutorService shutdown")
        }
        coroutineDispatcher.close()
    }
}