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
    fun `read and write through strategy`() = useReadAndWriteThroughStrategy(appManager)

    @Test
    fun `read through and write around strategy`() = useReadThroughAndWriteAroundStrategy(appManager)

    @Test
    fun `read through and write behind strategy`() = useReadThroughAndWriteBehindStrategy(appManager)

    @Test
    fun `cache aside strategy`() = useCacheAsideStrategy(appManager)
}