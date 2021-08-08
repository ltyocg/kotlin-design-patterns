package com.ltyocg.activeobject

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

private const val NUM_CREATURES = 3

fun main() = runBlocking {
    val creatures = (0 until NUM_CREATURES)
        .map { Orc(Orc::class.simpleName + it) }
        .onEach {
            it.eat()
            it.roam()
        }
        .toList()
    delay(1000)
    creatures.forEach { it.kill() }
}