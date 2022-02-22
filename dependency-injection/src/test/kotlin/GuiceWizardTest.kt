import com.google.inject.AbstractModule
import com.google.inject.Guice
import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import kotlin.test.Test

class GuiceWizardTest {
    @Test
    fun `test smoke everything through constructor`() {
        listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
            assertLogContains(Level.INFO, "AdvancedSorceress smoking ${it::class.simpleName}") {
                GuiceWizard(it).smoke()
            }
        }
    }

    @Test
    fun `test smoke everything through injection framework`() {
        listOf(OldTobyTobacco::class, RivendellTobacco::class, SecondBreakfastTobacco::class).forEach {
            assertLogContains(Level.INFO, "GuiceWizard smoking ${it.simpleName}") {
                Guice.createInjector(object : AbstractModule() {
                    override fun configure() {
                        bind(Tobacco::class.java).to(it.java)
                    }
                }).getInstance(GuiceWizard::class.java).smoke()
            }
        }
    }
}