package com.ltyocg.datatransfer.customer

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CustomerResourceTest {
    @Test
    fun `should get allCustomers`() {
        with(CustomerResource(mutableListOf(CustomerDto("1", "Melody", "Yates"))).allCustomers) {
            assertEquals(1, size)
            assertEquals("1", this[0].id)
            assertEquals("Melody", this[0].firstName)
            assertEquals("Yates", this[0].lastName)
        }
    }

    @Test
    fun `should save customer`() {
        val customerResource = CustomerResource(mutableListOf())
        customerResource.save(CustomerDto("1", "Rita", "Reynolds"))
        with(customerResource.allCustomers) {
            assertEquals("1", this[0].id)
            assertEquals("Rita", this[0].firstName)
            assertEquals("Reynolds", this[0].lastName)
        }
    }

    @Test
    fun `should delete customer`() {
        val customer = CustomerDto("1", "Terry", "Nguyen")
        val customerResource = CustomerResource(mutableListOf(customer))
        customerResource.delete(customer.id)
        assertTrue(customerResource.allCustomers.isEmpty())
    }
}