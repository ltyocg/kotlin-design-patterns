package orchestration

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val sagaOrchestrator = SagaOrchestrator(newSaga(), serviceDiscovery())
    logger.info { "orders: goodOrder is ${sagaOrchestrator.execute("good_order")}, badOrder is ${sagaOrchestrator.execute("bad_order")},crashedOrder is ${sagaOrchestrator.execute("crashed_order")}" }
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
