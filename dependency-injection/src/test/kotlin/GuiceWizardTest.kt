import com.ltyocg.commons.assertLogContains
import org.slf4j.event.Level
import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans
import kotlin.test.Test

class GuiceWizardTest {
    @Test
    fun `test smoke everything through constructor`() {
        listOf(OldTobyTobacco(), RivendellTobacco(), SecondBreakfastTobacco()).forEach {
            assertLogContains(Level.INFO, "GuiceWizard smoking ${it::class.simpleName}") {
                GuiceWizard(it).smoke()
            }
        }
    }

    @Test
    fun `test smoke everything through injection framework`() {
        listOf(OldTobyTobacco::class, RivendellTobacco::class, SecondBreakfastTobacco::class).forEach {
            assertLogContains(Level.INFO, "GuiceWizard smoking ${it.simpleName}") {
                StaticApplicationContext().apply {
                    beans {
                        registerBean(it.java, *emptyArray())
                        bean<GuiceWizard>()
                    }.initialize(this)
                }.getBean<GuiceWizard>().smoke()
            }
        }
    }
}