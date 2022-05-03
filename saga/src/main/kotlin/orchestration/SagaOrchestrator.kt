package orchestration

import org.slf4j.LoggerFactory

class SagaOrchestrator(private val saga: Saga, private val sd: ServiceDiscoveryService) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val state = CurrentState()
    fun <K> execute(value: K): Saga.Result {
        state.cleanUp()
        log.info(" The new saga is about to start")
        var result = Saga.Result.FINISHED
        var tempVal = value
        while (true) {
            var next = state.current()
            val ch = saga[next]

            @Suppress("UNCHECKED_CAST")
            val srv = sd.find(ch.name) as OrchestrationChapter<K>?
            if (srv == null) {
                state.directionToBack()
                state.back()
                continue
            }
            if (state.isForward) {
                val processRes = srv.process(tempVal)
                if (processRes.isSuccess) {
                    next = state.forward()
                    tempVal = processRes.value
                } else state.directionToBack()
            } else {
                val rlRes = srv.rollback(tempVal)
                if (rlRes.isSuccess) tempVal = rlRes.value as K
                else result = Saga.Result.CRASHED
                next = state.back()
            }
            if (!saga.isPresent(next)) return when {
                state.isForward -> Saga.Result.FINISHED
                result == Saga.Result.CRASHED -> Saga.Result.CRASHED
                else -> Saga.Result.ROLLBACK
            }
        }
    }

    private class CurrentState {
        var currentNumber = 0
        var isForward = true
        fun cleanUp() {
            currentNumber = 0
            isForward = true
        }

        fun directionToBack() {
            isForward = false
        }

        fun forward(): Int = ++currentNumber
        fun back(): Int = --currentNumber
        fun current(): Int = currentNumber
    }
}