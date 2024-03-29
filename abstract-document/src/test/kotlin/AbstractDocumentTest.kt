import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class AbstractDocumentTest {
    private class DocumentImplementation(properties: Map<String, Any?>) : AbstractDocument(properties)

    private val document = DocumentImplementation(emptyMap())

    private companion object {
        private const val KEY = "key"
        private const val VALUE = "value"
    }

    @Test
    fun `should put and get value`() {
        document[KEY] = VALUE
        assertEquals(VALUE, document[KEY])
    }

    @Test
    fun `should retrieve children`() {
        document[KEY] = arrayListOf<Map<String, Any?>>(emptyMap(), emptyMap())
        assertEquals(2, document.children(KEY, AbstractDocumentTest::DocumentImplementation).count())
    }

    @Test
    fun `should retrieve empty stream for non existing children`() =
        assertEquals(0, document.children(KEY, AbstractDocumentTest::DocumentImplementation).count())

    @Test
    fun `should include props in toString`() {
        val document = DocumentImplementation(mapOf(KEY to VALUE))
        assertContains(document.toString(), KEY)
        assertContains(document.toString(), VALUE)
    }
}