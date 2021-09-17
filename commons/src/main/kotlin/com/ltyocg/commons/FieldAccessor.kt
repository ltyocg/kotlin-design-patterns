package com.ltyocg.commons

import java.util.*

class FieldAccessor<T : Any>
private constructor(@PublishedApi internal val obj: T) {
    inline operator fun <reified R> get(name: String): R = obj::class.java.getDeclaredField(name).let {
        it.isAccessible = true
        it[obj] as R
    }

    operator fun set(name: String, value: Any?) {
        obj::class.java.getDeclaredField(name).also {
            it.isAccessible = true
            it[obj] = value
        }
    }

    companion object {
        private val map = WeakHashMap<Any, FieldAccessor<*>>()

        @Suppress("UNCHECKED_CAST")
        operator fun <T : Any> invoke(obj: T): FieldAccessor<T> = map.computeIfAbsent(obj, ::FieldAccessor) as FieldAccessor<T>
    }
}