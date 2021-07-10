package com.ltyocg.abstractdocument

import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

private const val KEY = "key"
private const val VALUE = "value"

class AbstractDocumentTest {
    private class DocumentImplementation(properties: Map<String, Any?>) : AbstractDocument(properties)

    private val document = DocumentImplementation(emptyMap())

    @Test
    fun `should put and get value`() {
        document.put(KEY, VALUE)
        assertEquals(VALUE, document.get(KEY))
    }

    @Test
    fun `should retrieve children`() {
        document.put(KEY, arrayListOf<Map<String, Any?>>(emptyMap(), emptyMap()))
        assertEquals(2, document.children(KEY, ::DocumentImplementation).count())
    }

    @Test
    fun `should retrieve empty stream for non existing children`() {
        assertEquals(0, document.children(KEY, ::DocumentImplementation).count())
    }

    @Test
    fun `should include props in to string`() {
        val document = DocumentImplementation(mapOf(KEY to VALUE))
        assertContains(document.toString(), KEY)
        assertContains(document.toString(), VALUE)
    }
}