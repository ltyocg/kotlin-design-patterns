package orchestration

import io.github.oshai.kotlinlogging.KotlinLogging

abstract class Service<K> : OrchestrationChapter<K> {
    private val logger = KotlinLogging.logger {}
    abstract override val name: String
    override fun process(value: K): ChapterResult<K> {
        logger.info { "The chapter '$name' has been started. The data $value has been stored or calculated successfully" }
        return ChapterResult.success(value)
    }

    override fun rollback(value: K): ChapterResult<K> {
        logger.info { "The Rollback for a chapter '$name' has been started. The data $value has been rollbacked successfully" }
        return ChapterResult.success(value)
    }
}

class FlyBookingService : Service<String>() {
    override val name = "booking a Fly"
}

class HotelBookingService : Service<String>() {
    private val logger = KotlinLogging.logger {}
    override val name = "booking a Hotel"
    override fun rollback(value: String): ChapterResult<String> {
        if (value == "crashed_order") {
            logger.info { "The Rollback for a chapter '$name' has been started. The data $value has been failed.The saga has been crashed." }
            return ChapterResult.failure(value)
        }
        logger.info { "The Rollback for a chapter '$name' has been started. The data $value has been rollbacked successfully" }
        return super.rollback(value)
    }
}

class OrderService : Service<String>() {
    override val name = "init an order"
}

class WithdrawMoneyService : Service<String>() {
    private val logger = KotlinLogging.logger {}
    override val name = "withdrawing Money"
    override fun process(value: String): ChapterResult<String> {
        if (value == "bad_order" || value == "crashed_order") {
            logger.info { "The chapter '$name' has been started. But the exception has been raised.The rollback is about to start" }
            return ChapterResult.failure(value)
        }
        return super.process(value)
    }
}
