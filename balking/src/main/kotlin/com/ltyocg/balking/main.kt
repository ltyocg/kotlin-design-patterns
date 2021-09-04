package com.ltyocg.balking

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val washingMachine = WashingMachine()
    repeat(3) {
        launch { washingMachine.wash() }
    }
}