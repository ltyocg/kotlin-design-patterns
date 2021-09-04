package com.ltyocg.circuitbreaker

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val serverStartTime = System.nanoTime()
    val delayedServiceCircuitBreaker = DefaultCircuitBreaker(DelayedRemoteService(serverStartTime, 5), 2, 2000L * 1000 * 1000)
    val quickServiceCircuitBreaker = DefaultCircuitBreaker(QuickRemoteService(), 2, 2000L * 1000 * 1000)
    val monitoringService = MonitoringService(delayedServiceCircuitBreaker, quickServiceCircuitBreaker)
    log.info(monitoringService.localResourceResponse())
    log.info(monitoringService.delayedServiceResponse())
    log.info(monitoringService.delayedServiceResponse())
    log.info(delayedServiceCircuitBreaker.state.name)
    log.info(monitoringService.quickServiceResponse())
    log.info(quickServiceCircuitBreaker.state.name)
    log.info("Waiting for delayed service to become responsive")
    Thread.sleep(5000)
    log.info(delayedServiceCircuitBreaker.state.name)
    log.info(monitoringService.delayedServiceResponse())
    log.info(delayedServiceCircuitBreaker.state.name)
}