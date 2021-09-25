package com.ltyocg.eda

fun main() {
    with(EventDispatcher()) {
        registerHandler(UserUpdatedEventHandler())
        registerHandler(UserCreatedEventHandler())
        val user = User("ltyocg")
        dispatch(UserCreatedEvent(user))
        dispatch(UserUpdatedEvent(user))
    }
}