package com.ltyocg.circuitbreaker

interface CircuitBreaker {
    var state: State
    fun recordSuccess()
    fun recordFailure(response: String?)
    fun attemptRequest(): String?
}

class DefaultCircuitBreaker(
    private val service: RemoteService?,
    private val failureThreshold: Int,
    private val retryTimePeriod: Long
) : CircuitBreaker {
    private val futureTime = 1000L * 1000 * 1000 * 1000
    var lastFailureTime: Long = System.nanoTime() + futureTime
    private var lastFailureResponse: String? = null
    var failureCount = 0
    private var _state = State.CLOSED
    override var state: State
        get() {
            evaluateState()
            return _state
        }
        set(value) {
            _state = value
            when (value) {
                State.OPEN -> {
                    failureCount = failureThreshold
                    lastFailureTime = System.nanoTime()
                }
                State.HALF_OPEN -> {
                    failureCount = failureThreshold
                    lastFailureTime = System.nanoTime() - retryTimePeriod
                }
                State.CLOSED -> failureCount = 0
            }
        }

    override fun recordSuccess() {
        failureCount = 0
        lastFailureTime = System.nanoTime() + futureTime
        _state = State.CLOSED
    }

    override fun recordFailure(response: String?) {
        failureCount++
        lastFailureTime = System.nanoTime()
        lastFailureResponse = response
    }

    internal fun evaluateState() {
        _state = if (failureCount < failureThreshold) State.CLOSED
        else if (System.nanoTime() - lastFailureTime > retryTimePeriod) State.HALF_OPEN
        else State.OPEN
    }

    override fun attemptRequest(): String? {
        evaluateState()
        return if (state == State.OPEN) lastFailureResponse
        else try {
            service!!.call().also { recordSuccess() }
        } catch (e: RemoteServiceException) {
            recordFailure(e.localizedMessage)
            throw e
        }
    }
}

enum class State {
    CLOSED,
    OPEN,
    HALF_OPEN
}
