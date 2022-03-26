import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class NullNodeTest {
    @Test
    fun testFields() {
        val node = NullNode.instance
        assertEquals(0, node.treeSize)
        assertNull(node.name)
        assertNull(node.left)
        assertNull(node.right)
    }
}