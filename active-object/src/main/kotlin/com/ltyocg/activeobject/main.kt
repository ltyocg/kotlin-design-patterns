package com.ltyocg.activeobject

private const val NUM_CREATURES = 3

fun main() {
    val creatures = generateSequence(0) { it + 1 }
        .take(NUM_CREATURES)
        .map {
            Orc(Orc::class.simpleName + it).apply {
                eat()
                roam()
            }
        }
        .toList()
    Thread.sleep(1000)
    creatures.forEach { it.kill() }
}