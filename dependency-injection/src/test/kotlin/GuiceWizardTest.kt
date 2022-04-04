import com.ltyocg.commons.assertLogContains
import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans
import kotlin.test.Test

class GuiceWizardTest {
    @Test
    fun `smoke everything through constructor`() = listOf(
        OldTobyTobacco(),
        RivendellTobacco(),
        SecondBreakfastTobacco()
    ).forEach {
        assertLogContains("GuiceWizard smoking ${it::class.simpleName}") {
            GuiceWizard(it).smoke()
        }
    }

    @Test
    fun `smoke everything through injection framework`() = listOf(
        OldTobyTobacco::class,
        RivendellTobacco::class,
        SecondBreakfastTobacco::class
    ).forEach {
        assertLogContains("GuiceWizard smoking ${it.simpleName}") {
            StaticApplicationContext().apply {
                beans {
                    registerBean(it.java, *emptyArray())
                    bean<GuiceWizard>()
                }.initialize(this)
            }.getBean<GuiceWizard>().smoke()
        }
    }
}