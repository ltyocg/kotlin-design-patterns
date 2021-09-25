package com.ltyocg.event.aggregator

interface EventObserver {
    fun onEvent(e: Event?)
}