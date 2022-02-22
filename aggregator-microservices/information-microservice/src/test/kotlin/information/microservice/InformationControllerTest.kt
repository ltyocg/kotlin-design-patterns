package information.microservice

import kotlin.test.Test
import kotlin.test.assertEquals

class InformationControllerTest {
    @Test
    fun `should getProductTitle`() {
        assertEquals("The Product Title.", InformationController().getProductTitle())
    }
}