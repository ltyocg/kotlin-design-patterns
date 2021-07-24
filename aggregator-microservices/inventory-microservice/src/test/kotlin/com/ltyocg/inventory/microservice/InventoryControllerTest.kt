package com.ltyocg.inventory.microservice

import kotlin.test.Test
import kotlin.test.assertEquals

class InventoryControllerTest {
    @Test
    fun `test get product inventories`() {
        assertEquals(5, InventoryController().getProductInventories())
    }
}