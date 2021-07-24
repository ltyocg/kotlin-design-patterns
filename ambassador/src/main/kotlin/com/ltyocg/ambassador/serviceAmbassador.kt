package com.ltyocg.ambassador

import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis

private const val RETRIES = 3
private const val DELAY_MS = 3000L

class ServiceAmbassador : RemoteServiceInterface {
    private val log = LoggerFactory.getLogger(this::class.java)

    override fun doRemoteFunction(value: Int): Long {
        var result = RemoteServiceStatus.FAILURE.remoteServiceStatusValue
        for (i in 0 until RETRIES) {
            log.info("Time taken (ms): {}", measureTimeMillis {
                result = RemoteService.doRemoteFunction(value)
            })
            if (result == RemoteServiceStatus.FAILURE.remoteServiceStatusValue) {
                log.info("Failed to reach remote: ({})", i + 1)
                try {
                    Thread.sleep(DELAY_MS)
                } catch (e: InterruptedException) {
                    log.error("Thread sleep state interrupted", e)
                    Thread.currentThread().interrupt()
                }
            } else break
        }
        return result
    }
}