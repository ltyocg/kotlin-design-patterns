import database.DbManagerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test

class CachingTest {
    private lateinit var appManager: AppManager

    @BeforeTest
    fun setup() {
        appManager = AppManager(DbManagerFactory.initDb(false)).apply { initDb() }
    }

    @Test
    fun `test read and write through strategy`() = useReadAndWriteThroughStrategy(appManager)

    @Test
    fun `test read through and write around strategy`() = useReadThroughAndWriteAroundStrategy(appManager)

    @Test
    fun `test read through and write behind strategy`() = useReadThroughAndWriteBehindStrategy(appManager)

    @Test
    fun `test cache aside strategy`() = useCacheAsideStrategy(appManager)
}