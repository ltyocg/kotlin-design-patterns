package com.ltyocg.composite

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    with(Messenger()) {
        log.info("Message from the orcs: ")
        messageFromOrcs().print()
        log.info("Message from the elves: ")
        messageFromElves().print()
    }
}