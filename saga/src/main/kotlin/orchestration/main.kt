package orchestration

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val sagaOrchestrator = SagaOrchestrator(newSaga(), serviceDiscovery())
    log.info(
        "orders: goodOrder is {}, badOrder is {},crashedOrder is {}",
        sagaOrchestrator.execute("good_order"),
        sagaOrchestrator.execute("bad_order"),
        sagaOrchestrator.execute("crashed_order")
    )
}

private fun newSaga(): Saga = Saga()
    .chapter("init an order")
    .chapter("booking a Fly")
    .chapter("booking a Hotel")
    .chapter("withdrawing Money")

private fun serviceDiscovery(): ServiceDiscoveryService = ServiceDiscoveryService()
    .discover(OrderService())
    .discover(FlyBookingService())
    .discover(HotelBookingService())
    .discover(WithdrawMoneyService())