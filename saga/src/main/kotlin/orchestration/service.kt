package orchestration

import org.slf4j.LoggerFactory

abstract class Service<K> : OrchestrationChapter<K> {
    private val log = LoggerFactory.getLogger(javaClass)
    abstract override val name: String
    override fun process(value: K): ChapterResult<K> {
        log.info("The chapter '{}' has been started. The data {} has been stored or calculated successfully", name, value)
        return ChapterResult.success(value)
    }

    override fun rollback(value: K): ChapterResult<K> {
        log.info("The Rollback for a chapter '{}' has been started. The data {} has been rollbacked successfully", name, value)
        return ChapterResult.success(value)
    }
}

class FlyBookingService : Service<String>() {
    override val name = "booking a Fly"
}

class HotelBookingService : Service<String>() {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "booking a Hotel"
    override fun rollback(value: String): ChapterResult<String> {
        if (value == "crashed_order") {
            log.info("The Rollback for a chapter '{}' has been started. The data {} has been failed.The saga has been crashed.", name, value)
            return ChapterResult.failure(value)
        }
        log.info("The Rollback for a chapter '{}' has been started. The data {} has been rollbacked successfully", name, value)
        return super.rollback(value)
    }
}

class OrderService : Service<String>() {
    override val name = "init an order"
}

class WithdrawMoneyService : Service<String>() {
    private val log = LoggerFactory.getLogger(javaClass)
    override val name = "withdrawing Money"
    override fun process(value: String): ChapterResult<String> {
        if (value == "bad_order" || value == "crashed_order") {
            log.info("The chapter '{}' has been started. But the exception has been raised.The rollback is about to start", name)
            return ChapterResult.failure(value)
        }
        return super.process(value)
    }
}