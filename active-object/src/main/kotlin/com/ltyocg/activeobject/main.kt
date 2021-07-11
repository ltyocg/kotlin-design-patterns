package com.ltyocg.activeobject

private const val NUM_CREATURES = 3

fun main() {
    val creatures = (0 until NUM_CREATURES)
        .map {
            Orc(Orc::class.simpleName + it).also { orc ->
                orc.eat()
                orc.roam()
            }
        }
        .toList()
    Thread.sleep(1000)
    creatures.forEach { it.kill() }
}