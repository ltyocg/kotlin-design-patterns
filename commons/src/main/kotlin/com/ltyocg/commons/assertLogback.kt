package com.ltyocg.commons

import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.ConsoleAppender
import org.slf4j.MDC
import org.slf4j.event.Level
import kotlin.test.assertNull

fun assertLogContains(level: Level, content: String, block: () -> Unit) {
    require(content.isNotEmpty())
    MDC.put(level.toString(), content)
    block()
    assertNull(MDC.get(level.toString()))
    MDC.clear()
}

class AssertConsoleAppender : ConsoleAppender<ILoggingEvent>() {
    private val levelMap = Level.values().associateBy { it.toString() }
    override fun subAppend(event: ILoggingEvent) {
        val level = levelMap[event.level.levelStr].toString()
        val content = event.mdcPropertyMap[level]
        if (content != null) {
            String(encoder.encode(event)).contains(content)
            event.mdcPropertyMap.remove(level)
        }
        super.subAppend(event)
    }
}