import org.junit.jupiter.api.BeforeAll
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SkeletonTest {
    companion object {
        private lateinit var skeleton: Skeleton

        @JvmStatic
        @BeforeAll
        fun setup() {
            skeleton = Skeleton(1)
        }
    }

    @Test
    fun `update for patrolling left`() {
        skeleton.patrollingLeft = true
        skeleton.position = 50
        skeleton.update()
        assertEquals(49, skeleton.position)
    }

    @Test
    fun `update for patrolling right`() {
        skeleton.patrollingLeft = false
        skeleton.position = 50
        skeleton.update()
        assertEquals(51, skeleton.position)
    }

    @Test
    fun `update for reverse direction from left to right`() {
        skeleton.patrollingLeft = true
        skeleton.position = 1
        skeleton.update()
        assertEquals(0, skeleton.position)
        assertFalse(skeleton.patrollingLeft)
    }

    @Test
    fun `update for reverse direction from right to left`() {
        skeleton.patrollingLeft = false
        skeleton.position = 99
        skeleton.update()
        assertEquals(100, skeleton.position)
        assertTrue(skeleton.patrollingLeft)
    }
}