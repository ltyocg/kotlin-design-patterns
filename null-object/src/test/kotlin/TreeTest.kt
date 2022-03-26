import com.ltyocg.commons.logContents
import kotlin.test.*

class TreeTest {
    private val treeRoot = NodeImpl(
        "root",
        NodeImpl(
            "level1_a",
            NodeImpl(
                "level2_a",
                NodeImpl("level3_a", NullNode.instance, NullNode.instance),
                NodeImpl("level3_b", NullNode.instance, NullNode.instance)
            ),
            NodeImpl("level2_b", NullNode.instance, NullNode.instance)
        ),
        NodeImpl("level1_b", NullNode.instance, NullNode.instance)
    )

    @Test
    fun testTreeSize() = assertEquals(7, treeRoot.treeSize)

    @Test
    fun testWalk() {
        val list = logContents { treeRoot.walk() }
        arrayOf(
            "root",
            "level1_a",
            "level2_a",
            "level3_a",
            "level3_b",
            "level2_b",
            "level1_b"
        ).forEach { assertContains(list, it) }
        assertEquals(7, list.size)
    }

    @Test
    fun `get left`() {
        val level1 = treeRoot.left
        assertEquals("level1_a", level1.name)
        assertEquals(5, level1.treeSize)
        val level2 = level1.left
        assertNotNull(level2)
        assertEquals("level2_a", level2.name)
        assertEquals(3, level2.treeSize)
        val level3 = level2.left
        assertNotNull(level3)
        assertEquals("level3_a", level3.name)
        assertEquals(1, level3.treeSize)
        assertSame(NullNode.instance, level3.right)
        assertSame(NullNode.instance, level3.left)
    }

    @Test
    fun `get right`() {
        val level1 = treeRoot.right
        assertEquals("level1_b", level1.name)
        assertEquals(1, level1.treeSize)
        assertSame(NullNode.instance, level1.right)
        assertSame(NullNode.instance, level1.left)
    }
}