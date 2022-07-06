import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.*

class TreeTest {
    private val treeRoot = node(
        "root",
        node(
            "level1_a",
            node(
                "level2_a",
                node("level3_a", NullNode, NullNode),
                node("level3_b", NullNode, NullNode)
            ),
            node("level2_b", NullNode, NullNode)
        ),
        node("level1_b", NullNode, NullNode)
    )

    @Test
    fun treeSize() = assertEquals(7, treeRoot.treeSize)

    @Test
    fun walk() {
        val assertListAppender = assertListAppender(NodeImpl::class)
        treeRoot.walk()
        assertContentEquals(
            listOf("root", "level1_a", "level2_a", "level3_a", "level3_b", "level2_b", "level1_b"),
            assertListAppender.formattedList()
        )
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
        assertSame(NullNode, level3.right)
        assertSame(NullNode, level3.left)
    }

    @Test
    fun `get right`() {
        val level1 = treeRoot.right
        assertEquals("level1_b", level1.name)
        assertEquals(1, level1.treeSize)
        assertSame(NullNode, level1.right)
        assertSame(NullNode, level1.left)
    }
}