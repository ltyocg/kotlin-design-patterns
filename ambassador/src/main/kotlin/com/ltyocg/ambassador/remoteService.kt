package com.ltyocg.ambassador

import org.slf4j.LoggerFactory
import kotlin.math.floor

private const val THRESHOLD = 200

object RemoteService : RemoteServiceInterface {
    private val log = LoggerFactory.getLogger(this::class.java)
    var randomProvider: () -> Double = Math::random

    override fun doRemoteFunction(value: Int): Long {
        val waitTime = floor(randomProvider() * 1000).toLong()
        try {
            Thread.sleep(waitTime)
        } catch (e: InterruptedException) {
            log.error("Thread sleep state interrupted", e)
            Thread.currentThread().interrupt()
        }
        return if (waitTime <= THRESHOLD) value * 10L else RemoteServiceStatus.FAILURE.remoteServiceStatusValue
    }
}