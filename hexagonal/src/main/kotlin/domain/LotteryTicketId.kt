package domain

import java.util.concurrent.atomic.AtomicInteger

data class LotteryTicketId(val id: Int = numAllocated.incrementAndGet()) {
    private companion object {
        private val numAllocated = AtomicInteger(0)
    }

    override fun toString(): String = id.toString()
}