import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.formattedList
import kotlin.test.Test
import kotlin.test.assertContentEquals

class WizardTowerProxyTest {
    @Test
    fun enter() {
        val proxy = WizardTowerProxy(IvoryTower)
        val assertListAppender = assertListAppender(IvoryTower::class, WizardTowerProxy::class)
        listOf("Gandalf", "Dumbledore", "Oz", "Merlin").map(::Wizard).forEach(proxy::enter)
        assertContentEquals(
            listOf(
                "Gandalf enters the tower.",
                "Dumbledore enters the tower.",
                "Oz enters the tower.",
                "Merlin is not allowed to enter!"
            ),
            assertListAppender.formattedList()
        )
    }
}