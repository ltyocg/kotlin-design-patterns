package choreography

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val service = serviceDiscovery().findAny()
    log.info(
        "orders: goodOrder is {}, badOrder is {}",
        service.execute(newSaga("good_order")).result,
        service.execute(newSaga("bad_order")).result
    )
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