import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.lastMessage
import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans
import kotlin.test.Test
import kotlin.test.assertEquals

class GuiceWizardTest {
    @Test
    fun `smoke everything through constructor`() {
        val assertListAppender = assertListAppender {
            bind<Tobacco>(true)
        }
        listOf(OldTobyTobacco, RivendellTobacco, SecondBreakfastTobacco).forEach {
            GuiceWizard(it).smoke()
            assertEquals("GuiceWizard smoking ${it::class.simpleName}", assertListAppender.lastMessage())
        }
    }

    @Test
    fun `smoke everything through injection framework`() {
        val assertListAppender = assertListAppender {
            bind<Tobacco>(true)
        }
        listOf(
            OldTobyTobacco::class,
            RivendellTobacco::class,
            SecondBreakfastTobacco::class
        ).forEach {
            StaticApplicationContext().apply {
                beans {
                    registerBean(it.java, *emptyArray())
                    bean<GuiceWizard>()
                }.initialize(this)
            }.getBean<GuiceWizard>().smoke()
            assertEquals("GuiceWizard smoking ${it.simpleName}", assertListAppender.lastMessage())
        }
    }
}