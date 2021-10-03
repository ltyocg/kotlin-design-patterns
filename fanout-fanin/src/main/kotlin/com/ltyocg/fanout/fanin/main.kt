package com.ltyocg.fanout.fanin

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val numbers = listOf(1L, 3L, 4L, 7L, 8L)
    log.info("Numbers to be squared and get sum --> {}", numbers)
    log.info("Sum of all squared numbers --> {}", FanOutFanIn.fanOutFanIn(numbers.map(::SquareNumberRequest), Consumer(0L)))
}