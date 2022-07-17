import java.security.InvalidParameterException
import kotlin.test.Test
import kotlin.test.assertFailsWith

class BarCustomerTest {
    @Test
    fun constructor() {
        assertFailsWith<InvalidParameterException> {
            BarCustomer("sirBrave", -1, CallsCount())
        }
    }
}