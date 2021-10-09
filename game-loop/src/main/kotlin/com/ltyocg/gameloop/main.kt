package com.ltyocg.gameloop

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

private const val GAME_LOOP_DURATION_TIME = 2000L
private val log = LoggerFactory.getLogger("main")

fun main() = runBlocking {
    log.info("Start frame-based game loop:")
    FrameBasedGameLoop().runPeriod()
    log.info("Stop frame-based game loop.")
    log.info("Start variable-step game loop:")
    VariableStepGameLoop().runPeriod()
    log.info("Stop variable-step game loop.")
    log.info("Start fixed-step game loop:")
    FixedStepGameLoop().runPeriod()
    log.info("Stop variable-step game loop.")
}

private suspend fun GameLoop.runPeriod() {
    run()
    delay(GAME_LOOP_DURATION_TIME)
    stop()
}