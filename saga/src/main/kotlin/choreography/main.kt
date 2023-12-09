package choreography

import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val service = serviceDiscovery().findAny()
    logger.info { "orders: goodOrder is ${service.execute(newSaga("good_order")).result}, badOrder is ${service.execute(newSaga("bad_order")).result}" }
}

private fun newSaga(value: Any?): Saga = Saga()
    .chapter("init an order").setInValue(value)
    .chapter("booking a Fly")
    .chapter("booking a Hotel")
    .chapter("withdrawing Money")

private fun serviceDiscovery(): ServiceDiscoveryService {
    val sd = ServiceDiscoveryService()
    return sd
        .discover(OrderService(sd))
        .discover(FlyBookingService(sd))
        .discover(HotelBookingService(sd))
        .discover(WithdrawMoneyService(sd))
}
