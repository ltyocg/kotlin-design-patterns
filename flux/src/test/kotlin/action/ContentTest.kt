package action

import kotlin.test.Test
import kotlin.test.assertFalse

class ContentTest {
    @Test
    fun `test toString`() {
        Content.values().map(Any::toString).forEach {
            assertFalse(it.trim().isEmpty())
        }
    }
}