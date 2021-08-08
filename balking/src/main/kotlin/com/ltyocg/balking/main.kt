package com.ltyocg.balking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val washingMachine = WashingMachine()
    repeat(3) {
        launch(Dispatchers.Default) { washingMachine.wash() }
    }
}