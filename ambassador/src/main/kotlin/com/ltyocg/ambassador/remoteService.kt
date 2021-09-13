package com.ltyocg.ambassador

import kotlinx.coroutines.delay
import kotlin.math.floor

private const val THRESHOLD = 200

object RemoteService : RemoteServiceInterface {
    var randomProvider: () -> Double = Math::random
    override suspend fun doRemoteFunction(value: Int): Long {
        val waitTime = floor(randomProvider() * 1000).toLong()
        delay(waitTime)
        return if (waitTime <= THRESHOLD) value * 10L else RemoteServiceStatus.FAILURE.remoteServiceStatusValue
    }
}