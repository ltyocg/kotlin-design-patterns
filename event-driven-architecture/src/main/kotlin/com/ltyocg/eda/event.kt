package com.ltyocg.eda

import kotlin.reflect.KClass

interface Event {
    val type: KClass<out Event>
}

abstract class AbstractEvent : Event {
    override val type: KClass<out Event>
        get() = this::class
}

class UserCreatedEvent(val user: User) : AbstractEvent()
class UserUpdatedEvent(val user: User) : AbstractEvent()