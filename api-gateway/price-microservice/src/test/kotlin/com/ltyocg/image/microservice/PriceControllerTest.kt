package com.ltyocg.image.microservice

import com.ltyocg.price.microservice.PriceController
import kotlin.test.Test
import kotlin.test.assertEquals

class PriceControllerTest {
    @Test
    fun `test getPrice`() {
        assertEquals("20", PriceController().getPrice())
    }
}