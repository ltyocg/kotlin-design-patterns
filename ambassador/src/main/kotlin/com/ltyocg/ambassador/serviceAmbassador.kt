package com.ltyocg.ambassador

import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis

private const val RETRIES = 3
private const val DELAY_MS = 3000L

class ServiceAmbassador : RemoteServiceInterface {
    private val log = LoggerFactory.getLogger(this::class.java)
    override suspend fun doRemoteFunction(value: Int): Long {
        var result = RemoteServiceStatus.FAILURE.remoteServiceStatusValue
        for (i in 1..RETRIES) {
            log.info("Time taken (ms): {}", measureTimeMillis { result = RemoteService.doRemoteFunction(value) })
            if (result == RemoteServiceStatus.FAILURE.remoteServiceStatusValue) {
                log.info("Failed to reach remote: ({})", i)
                delay(DELAY_MS)
            } else break
        }
        return result
    }
}