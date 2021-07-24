package com.ltyocg.information.microservice

import kotlin.test.Test
import kotlin.test.assertEquals

class InformationControllerTest {
    @Test
    fun `should get product title`() {
        assertEquals("The Product Title.", InformationController().getProductTitle())
    }
}