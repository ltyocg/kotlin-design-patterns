package com.ltyocg.fanout.fanin

import java.util.concurrent.atomic.AtomicLong

class Consumer(init: Long) {
    val sumOfSquaredNumbers = AtomicLong(init)
    fun add(num: Long): Long = sumOfSquaredNumbers.addAndGet(num)
}