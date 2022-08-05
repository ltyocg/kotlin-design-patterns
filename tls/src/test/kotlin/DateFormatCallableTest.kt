import java.util.*
import java.util.concurrent.Executors
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class DateFormatCallableTest {
    private val expectedCounterExceptions = 5
    private lateinit var result: Result
    private lateinit var createdDateValues: List<String>

    @BeforeTest
    fun setup() {
        val executor = Executors.newCachedThreadPool()
        try {
            result = executor.submit(DateFormatCallable("dd/MM/yyyy", "15/12/2015")).get()
            createdDateValues = result.dateList.map {
                val cal = Calendar.getInstance().apply { time = it }
                "${cal[Calendar.DAY_OF_MONTH]}.${cal[Calendar.MONTH]}.${cal[Calendar.YEAR]}"
            }
        } catch (e: Exception) {
            fail("Setup failed: $e")
        }
        executor.shutdown()
    }

    @Test
    fun dateValues() = assertEquals(generateSequence { "15.11.2015" }.take(expectedCounterExceptions).toList(), createdDateValues)

    @Test
    fun `counter dateValues`() = assertEquals(expectedCounterExceptions, result.dateList.size)

    @Test
    fun `counter exceptions`() = assertEquals(0, result.exceptionList.size)
}