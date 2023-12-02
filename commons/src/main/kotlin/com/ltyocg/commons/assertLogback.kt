package com.ltyocg.commons

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import io.github.oshai.kotlinlogging.KotlinLogging
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

fun assertListAppender(vararg kClass: KClass<*>): ListAppender<ILoggingEvent> =
    ListAppender<ILoggingEvent>().apply {
        start()
        kClass.forEach { (LoggerFactory.getLogger(it.java) as Logger).addAppender(this) }
    }

fun assertListAppender(builderAction: ListAppenderBuilder.() -> Unit): ListAppender<ILoggingEvent> =
    assertListAppender(*ListAppenderBuilder().apply(builderAction).list.toTypedArray())

class ListAppenderBuilder
internal constructor() {
    @PublishedApi
    internal val logger = KotlinLogging.logger {}

    @PublishedApi
    internal val list = mutableListOf<KClass<*>>()
    inline fun <reified T> bind(sealedSubclasses: Boolean = false) {
        val kClass = T::class
        list.add(kClass)
        if (sealedSubclasses) {
            if (!kClass.isSealed) logger.warn { "$kClass is not a sealed class" }
            kClass.sealedSubclasses.forEach(list::add)
        }
    }
}

fun ListAppender<ILoggingEvent>.formattedList(): List<String> = list.map { it.formattedMessage }
fun ListAppender<ILoggingEvent>.lastMessage(): String? = list.lastOrNull()?.formattedMessage
fun ListAppender<ILoggingEvent>.clear() = list.clear()
