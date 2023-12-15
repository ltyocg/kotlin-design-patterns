import layers.App
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import kotlin.test.Test
import kotlin.test.assertNotNull

@SpringBootTest(classes = [App::class])
class MainTest(private val applicationContext: ApplicationContext) {
    @Test
    fun contextLoads() {
        assertNotNull(applicationContext)
    }
}
