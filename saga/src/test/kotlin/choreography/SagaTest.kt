package choreography

import kotlin.test.Test
import kotlin.test.assertEquals

class SagaTest {
    @Test
    fun execute() {
        val sd = ServiceDiscoveryService()
        val service = sd
            .discover(OrderService(sd))
            .discover(FlyBookingService(sd))
            .discover(HotelBookingService(sd))
            .discover(WithdrawMoneyService(sd)).findAny()
        assertEquals(Saga.SagaResult.ROLLBACKED, service.execute(newSaga("bad_order")).result)
        assertEquals(Saga.SagaResult.FINISHED, service.execute(newSaga("good_order")).result)
    }

    private fun newSaga(value: Any): Saga = Saga()
        .chapter("init an order").setInValue(value)
        .chapter("booking a Fly")
        .chapter("booking a Hotel")
        .chapter("withdrawing Money")
}