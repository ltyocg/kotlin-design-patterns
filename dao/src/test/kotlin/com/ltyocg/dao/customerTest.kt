package com.ltyocg.dao

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

private const val ID = 1
private const val FIRSTNAME = "Winston"
private const val LASTNAME = "Churchill"

class CustomerTest {
    private lateinit var customer: Customer

    @BeforeTest
    fun setUp() {
        customer = Customer(ID, FIRSTNAME, LASTNAME)
    }

    @Test
    fun `not equal with different id`() {
        val otherCustomer = Customer(2, FIRSTNAME, LASTNAME)
        assertNotEquals(customer, otherCustomer)
        assertNotEquals(customer.hashCode(), otherCustomer.hashCode())
    }

    @Test
    fun `equals with same object values`() {
        val otherCustomer = Customer(ID, FIRSTNAME, LASTNAME)
        assertEquals(customer, otherCustomer)
        assertEquals(customer.hashCode(), otherCustomer.hashCode())
    }

    @Test
    fun `equals with same objects`() {
        assertEquals(customer, customer)
        assertEquals(customer.hashCode(), customer.hashCode())
    }
}