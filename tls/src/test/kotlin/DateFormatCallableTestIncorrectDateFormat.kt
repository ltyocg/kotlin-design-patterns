import java.util.concurrent.Executors
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class DateFormatCallableTestIncorrectDateFormat {
    private val expectedCounterExceptions = 5
    private lateinit var result: Result

    @BeforeTest
    fun setup() {
        val executor = Executors.newCachedThreadPool()
        try {
            result = executor.submit(DateFormatCallable("dd/MM/yyyy", "15.12.2015")).get()
        } catch (e: Exception) {
            fail("Setup failed: $e")
        }
        executor.shutdown()
    }

    @Test
    fun exceptions() = assertEquals(
        generateSequence { "class java.text.ParseException: Unparseable date: \"15.12.2015\"" }.take(expectedCounterExceptions).toList(),
        result.exceptionList
    )

    @Test
    fun `counter dateValues`() = assertEquals(0, result.dateList.size)

    @Test
    fun `counter exceptions`() = assertEquals(expectedCounterExceptions, result.exceptionList.size)
}