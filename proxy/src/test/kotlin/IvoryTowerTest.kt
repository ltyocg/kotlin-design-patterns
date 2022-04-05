import com.ltyocg.commons.assertLogContentEquals
import kotlin.test.Test

class IvoryTowerTest {
    @Test
    fun enter() {
        val tower = IvoryTower()
        assertLogContentEquals(
            listOf(
                "Gandalf enters the tower.",
                "Dumbledore enters the tower.",
                "Oz enters the tower.",
                "Merlin enters the tower."
            )
        ) {
            listOf(
                Wizard("Gandalf"),
                Wizard("Dumbledore"),
                Wizard("Oz"),
                Wizard("Merlin")
            ).forEach(tower::enter)
        }
    }
}