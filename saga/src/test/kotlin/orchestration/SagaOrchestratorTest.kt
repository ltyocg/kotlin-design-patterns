package orchestration

import kotlin.test.Test
import kotlin.test.assertEquals

class SagaOrchestratorTest {
    @Test
    fun execute() {
        val sagaOrchestrator = SagaOrchestrator(
            Saga()
                .chapter("init an order")
                .chapter("booking a Fly")
                .chapter("booking a Hotel")
                .chapter("withdrawing Money"),
            ServiceDiscoveryService()
                .discover(OrderService())
                .discover(FlyBookingService())
                .discover(HotelBookingService())
                .discover(WithdrawMoneyService())
        )
        assertEquals(Saga.Result.ROLLBACK, sagaOrchestrator.execute("bad_order"))
        assertEquals(Saga.Result.CRASHED, sagaOrchestrator.execute("crashed_order"))
    }
}