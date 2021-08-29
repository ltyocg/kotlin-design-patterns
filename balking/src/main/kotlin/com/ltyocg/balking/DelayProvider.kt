package com.ltyocg.balking

import java.util.concurrent.TimeUnit

fun interface DelayProvider {
    fun executeAfterDelay(interval: Long, timeUnit: TimeUnit, task: () -> Unit)
}