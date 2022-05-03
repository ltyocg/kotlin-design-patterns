package choreography

import org.slf4j.LoggerFactory

abstract class Service(private val sd: ServiceDiscoveryService) : ChoreographyChapter {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun execute(saga: Saga): Saga {
        var nextSaga = saga
        val nextVal: Any?
        val chapterName = saga.current.name
        if (chapterName == name) {
            if (saga.isForward) {
                nextSaga = process(saga)
                nextVal = nextSaga.currentValue
                if (nextSaga.isCurrentSuccess) nextSaga.forward()
                else nextSaga.back()
            } else {
                nextSaga = rollback(saga)
                nextVal = nextSaga.currentValue
                nextSaga.back()
            }
            if (isSagaFinished(nextSaga)) return nextSaga
            nextSaga.currentValue = nextVal
        }
        return sd.find(chapterName)
            ?.execute(nextSaga)
            ?: throw RuntimeException("the service $chapterName has not been found")
    }

    override fun process(saga: Saga): Saga {
        val inValue = saga.currentValue
        log.info("The chapter '{}' has been started. The data {} has been stored or calculated successfully", name, inValue)
        saga.setCurrentStatus(Saga.ChapterResult.SUCCESS)
        saga.currentValue = inValue
        return saga
    }

    override fun rollback(saga: Saga): Saga {
        val inValue = saga.currentValue
        log.info("The Rollback for a chapter '{}' has been started. The data {} has been rollbacked successfully", name, inValue)
        saga.setCurrentStatus(Saga.ChapterResult.ROLLBACK)
        saga.currentValue = inValue
        return saga
    }

    private fun isSagaFinished(saga: Saga): Boolean {
        if (saga.isPresent) return false
        saga.finished = true
        log.info(" the saga has been finished with {} status", saga.result)
        return true
    }
}

class FlyBookingService(service: ServiceDiscoveryService) : Service(service) {
    override val name = "booking a Fly"
}

class HotelBookingService(service: ServiceDiscoveryService) : Service(service) {
    override val name = "booking a Hotel"
}

class OrderService(service: ServiceDiscoveryService) : Service(service) {
    override val name = "init an order"
}

class WithdrawMoneyService(service: ServiceDiscoveryService) : Service(service) {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "withdrawing Money"
    override fun process(saga: Saga): Saga {
        val inValue = saga.currentValue
        if (inValue == "bad_order") {
            log.info("The chapter '{}' has been started. But the exception has been raised.The rollback is about to start", name)
            saga.setCurrentStatus(Saga.ChapterResult.ROLLBACK)
            return saga
        }
        return super.process(saga)
    }
}