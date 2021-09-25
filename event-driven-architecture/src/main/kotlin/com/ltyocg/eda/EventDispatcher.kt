package com.ltyocg.eda

import kotlin.reflect.KClass

class EventDispatcher {
    @PublishedApi
    internal val handlers = mutableMapOf<KClass<out Event>, Handler<*>>()
    inline fun <reified E : Event> registerHandler(handler: Handler<E>) {
        handlers[E::class] = handler
    }

    fun <E : Event> dispatch(event: E) {
        @Suppress("UNCHECKED_CAST")
        (handlers[event::class] as Handler<E>?)?.onEvent(event)
    }
}