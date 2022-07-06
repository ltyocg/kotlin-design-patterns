import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContentEquals

class IvoryTowerTest {
    @Test
    fun enter() {
        val assertListAppender = assertListAppender(IvoryTower::class)
        listOf("Gandalf", "Dumbledore", "Oz", "Merlin").map(::Wizard).forEach(IvoryTower::enter)
        assertContentEquals(
            listOf(
                "Gandalf enters the tower.",
                "Dumbledore enters the tower.",
                "Oz enters the tower.",
                "Merlin enters the tower."
            ),
            assertListAppender.formattedList()
        )
    }
}