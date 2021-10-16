package com.ltyocg.intercepting.filter

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertSame

class FilterTest {
    companion object {
        private val PERFECT_ORDER = Order("name", "12345678901", "addr", "dep", "order")
        private val WRONG_ORDER = Order("name", "12345678901", "addr", "dep", "")
        private val WRONG_DEPOSIT = Order("name", "12345678901", "addr", "", "order")
        private val WRONG_ADDRESS = Order("name", "12345678901", "", "dep", "order")
        private val WRONG_CONTACT = Order("name", "", "addr", "dep", "order")
        private val WRONG_NAME = Order("", "12345678901", "addr", "dep", "order")

        @JvmStatic
        fun getTestData(): List<Array<Any>> = listOf(
            arrayOf(NameFilter(), PERFECT_ORDER, ""),
            arrayOf(NameFilter(), WRONG_NAME, "Invalid name!"),
            arrayOf(NameFilter(), WRONG_CONTACT, ""),
            arrayOf(NameFilter(), WRONG_ADDRESS, ""),
            arrayOf(NameFilter(), WRONG_DEPOSIT, ""),
            arrayOf(NameFilter(), WRONG_ORDER, ""),
            arrayOf(ContactFilter(), PERFECT_ORDER, ""),
            arrayOf(ContactFilter(), WRONG_NAME, ""),
            arrayOf(ContactFilter(), WRONG_CONTACT, "Invalid contact number!"),
            arrayOf(ContactFilter(), WRONG_ADDRESS, ""),
            arrayOf(ContactFilter(), WRONG_DEPOSIT, ""),
            arrayOf(ContactFilter(), WRONG_ORDER, ""),
            arrayOf(AddressFilter(), PERFECT_ORDER, ""),
            arrayOf(AddressFilter(), WRONG_NAME, ""),
            arrayOf(AddressFilter(), WRONG_CONTACT, ""),
            arrayOf(AddressFilter(), WRONG_ADDRESS, "Invalid address!"),
            arrayOf(AddressFilter(), WRONG_DEPOSIT, ""),
            arrayOf(AddressFilter(), WRONG_ORDER, ""),
            arrayOf(DepositFilter(), PERFECT_ORDER, ""),
            arrayOf(DepositFilter(), WRONG_NAME, ""),
            arrayOf(DepositFilter(), WRONG_CONTACT, ""),
            arrayOf(DepositFilter(), WRONG_ADDRESS, ""),
            arrayOf(DepositFilter(), WRONG_DEPOSIT, "Invalid deposit number!"),
            arrayOf(DepositFilter(), WRONG_ORDER, ""),
            arrayOf(OrderFilter(), PERFECT_ORDER, ""),
            arrayOf(OrderFilter(), WRONG_NAME, ""),
            arrayOf(OrderFilter(), WRONG_CONTACT, ""),
            arrayOf(OrderFilter(), WRONG_ADDRESS, ""),
            arrayOf(OrderFilter(), WRONG_DEPOSIT, ""),
            arrayOf(OrderFilter(), WRONG_ORDER, "Invalid order!")
        )
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun `test execute`(filter: Filter, order: Order, expectedResult: String) {
        assertEquals(expectedResult, filter.execute(order).trim())
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    fun `test next`(filter: Filter) {
        assertNull(filter.next)
        assertSame(filter, filter.last)
    }
}