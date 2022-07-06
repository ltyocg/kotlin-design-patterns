import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class NullNodeTest {
    @Test
    fun fields() {
        assertEquals(0, NullNode.treeSize)
        assertNull(NullNode.name)
        assertNull(NullNode.left)
        assertNull(NullNode.right)
    }
}