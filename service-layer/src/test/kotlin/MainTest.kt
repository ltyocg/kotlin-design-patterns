import kotlin.test.AfterTest
import kotlin.test.Test

class MainTest {
    @Test
    fun `should execute main without exception`() = main()

    @AfterTest
    fun tearDown() = HibernateUtil.dropSession()
}