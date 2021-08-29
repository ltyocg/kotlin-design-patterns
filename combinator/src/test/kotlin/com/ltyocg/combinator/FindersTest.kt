package com.ltyocg.combinator

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class FindersTest {
    @Test
    fun advancedFinder() {
        val res = Finders.advancedFinder("it was", "kingdom", "sea").find(text)
        assertEquals(1, res.size)
        assertEquals("It was many and many a year ago,", res[0])
    }

    @Test
    fun filteredFinder() {
        val res = Finders.filteredFinder(" was ", "many", "child").find(text)
        assertEquals(1, res.size)
        assertEquals("But we loved with a love that was more than love-", res[0])
    }

    @Test
    fun specializedFinder() {
        val res = Finders.specializedFinder("love", "heaven").find(text)
        assertEquals(1, res.size)
        assertEquals("With a love that the winged seraphs of heaven", res[0])
    }

    @Test
    fun expandedFinder() {
        val res = Finders.expandedFinder("It was", "kingdom").find(text)
        assertEquals(3, res.size)
        assertContentEquals(
            listOf(
                "It was many and many a year ago,",
                "In a kingdom by the sea,",
                "In this kingdom by the sea;"
            ), res
        )
    }
}