package com.ltyocg.fanout.fanin

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

object FanOutFanIn {
    fun fanOutFanIn(requests: List<SquareNumberRequest>, consumer: Consumer): Long {
        Executors.newFixedThreadPool(requests.size).asCoroutineDispatcher().use { coroutineDispatcher ->
            runBlocking(coroutineDispatcher) {
                requests.forEach { launch { it.delayedSquaring(consumer) } }
            }
        }
        return consumer.sumOfSquaredNumbers.get()
    }
}