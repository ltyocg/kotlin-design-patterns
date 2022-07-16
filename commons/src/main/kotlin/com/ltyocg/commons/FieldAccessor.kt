package com.ltyocg.commons

import java.util.*
import kotlin.reflect.KClass

class FieldAccessor<T : Any>
@PublishedApi internal constructor(
    @PublishedApi internal val obj: T,
    @PublishedApi internal val superKClass: KClass<in T>
) {
    inline operator fun <reified R> get(name: String): R = superKClass.java.getDeclaredField(name).let {
        it.isAccessible = true
        it[obj] as R
    }

    operator fun set(name: String, value: Any?) {
        superKClass.java.getDeclaredField(name).also {
            it.isAccessible = true
            it[obj] = value
        }
    }

    companion object {
        @PublishedApi
        internal val map = WeakHashMap<Any, FieldAccessor<*>>()

        @Suppress("UNCHECKED_CAST")
        inline operator fun <reified T : Any> invoke(obj: T, superKClass: KClass<in T> = T::class): FieldAccessor<T> =
            map.getOrPut(obj) { FieldAccessor(obj, superKClass) } as FieldAccessor<T>
    }
}