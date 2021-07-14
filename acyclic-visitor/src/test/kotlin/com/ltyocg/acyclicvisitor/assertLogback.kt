package com.ltyocg.acyclicvisitor

import ch.qos.logback.classic.spi.LoggingEvent
import ch.qos.logback.core.ConsoleAppender
import org.slf4j.event.Level
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong
import kotlin.test.assertTrue

private class Key
private constructor(val identity: Long, val level: Level) {
    override fun equals(other: Any?): Boolean = identity == (other as Key).identity
    override fun hashCode(): Int = identity.hashCode()

    companion object {
        private val atomicLong = AtomicLong()
        fun new(level: Level): Key = Key(atomicLong.addAndGet(1), level)
    }
}

private val logContentsMap = ConcurrentHashMap<Key, String>()

fun assertLogContains(level: Level, content: String, block: () -> Unit) {
    val key = Key.new(level)
    logContentsMap[key] = content
    block()
    assertTrue(!logContentsMap.containsKey(key))
    logContentsMap.remove(key)
}

fun assertLogClear() {
    logContentsMap.clear()
}

class AssertConsoleAppender<E> : ConsoleAppender<E>() {
    private val levelMap = Level.values().associateBy { it.toString() }

    override fun subAppend(event: E) {
        if (logContentsMap.isNotEmpty()) {
            val content = encoder.encode(event).toString(Charsets.UTF_8)
            val level = levelConvert(event)
            val iterator = logContentsMap.iterator()
            while (iterator.hasNext()) iterator.next().apply {
                if (key.level == level && content.contains(value)) iterator.remove()
            }
        }
        super.subAppend(event)
    }

    private fun levelConvert(event: E): Level? = levelMap[(event as LoggingEvent).level.levelStr]
}