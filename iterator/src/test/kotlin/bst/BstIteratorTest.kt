package bst

import org.junit.jupiter.api.TestInstance
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BstIteratorTest {
    private lateinit var nonEmptyRoot: TreeNode<Int>

    @BeforeTest
    fun createTrees() {
        nonEmptyRoot = TreeNode(5).apply {
            arrayOf(3, 7, 1, 4, 6).forEach(::insert)
        }
    }

    @Test
    fun `next over entire populated tree`() {
        with(BstIterator(nonEmptyRoot)) {
            assertEquals(1, next().value, "First Node is 1.")
            assertEquals(3, next().value, "Second Node is 3.")
            assertEquals(4, next().value, "Third Node is 4.")
            assertEquals(5, next().value, "Fourth Node is 5.")
            assertEquals(6, next().value, "Fifth Node is 6.")
            assertEquals(7, next().value, "Sixth Node is 7.")
        }
    }

    @Test
    fun `hasNext for populated tree`() {
        assertTrue(BstIterator(nonEmptyRoot).hasNext(), "hasNext() should return true for populated tree.")
    }

    @Test
    fun `next and hasNext over entire populated tree`() {
        with(BstIterator(nonEmptyRoot)) {
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(1, next().value, "First Node is 1.")
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(3, next().value, "Second Node is 3.")
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(4, next().value, "Third Node is 4.")
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(5, next().value, "Fourth Node is 5.")
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(6, next().value, "Fifth Node is 6.")
            assertTrue(hasNext(), "Iterator hasNext() should be true.")
            assertEquals(7, next().value, "Sixth Node is 7.")
        }
    }
}