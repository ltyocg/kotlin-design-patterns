import kotlin.test.Test
import kotlin.test.assertEquals

class BartenderTest {
    private val callsCount = CallsCount()

    @Test
    fun dummyCustomerApi() {
        val tenant = BarCustomer("pirate", 2, callsCount)
        val service = Bartender({}, callsCount)
        repeat(5) { service.orderDrink(tenant) }
        assertEquals(2, callsCount.getCount(tenant.name), "Counter limit must be reached")
    }
}