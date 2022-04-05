import com.ltyocg.commons.assertLogContentEquals
import kotlin.test.Test

class WizardTowerProxyTest {
    @Test
    fun enter() {
        val proxy = WizardTowerProxy(IvoryTower())
        assertLogContentEquals(
            listOf(
                "Gandalf enters the tower.",
                "Dumbledore enters the tower.",
                "Oz enters the tower.",
                "Merlin is not allowed to enter!"
            )
        ) {
            listOf(
                Wizard("Gandalf"),
                Wizard("Dumbledore"),
                Wizard("Oz"),
                Wizard("Merlin")
            ).forEach(proxy::enter)
        }
    }
}