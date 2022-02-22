package price.microservice

import kotlin.test.Test
import kotlin.test.assertEquals

class PriceControllerTest {
    @Test
    fun `test getPrice`() {
        assertEquals("20", PriceController().getPrice())
    }
}