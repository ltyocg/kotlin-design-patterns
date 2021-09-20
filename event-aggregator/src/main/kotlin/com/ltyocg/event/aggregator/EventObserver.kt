package com.ltyocg.event.aggregator

fun interface EventObserver {
    fun onEvent(e: Event?)
}