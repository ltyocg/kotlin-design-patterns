import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.Future
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class DateFormatCallableTestMultiThread {
    private class StringArrayList : ArrayList<String>()

    private val expectedCounterDateValues = 5
    private lateinit var result: Array<Result>
    private lateinit var createdDateValues: Array<StringArrayList>

    @BeforeTest
    fun setup() {
        val callableDf = DateFormatCallable("dd/MM/yyyy", "15/12/2015")
        val executor = Executors.newCachedThreadPool()
        try {
            result = generateSequence { executor.submit(callableDf) }
                .take(4)
                .map(Future<Result>::get)
                .toList()
                .toTypedArray()
            createdDateValues = result.map {
                it.dateList.map {
                    val cal = Calendar.getInstance().apply { time = it }
                    "${cal[Calendar.DAY_OF_MONTH]}.${cal[Calendar.MONTH]}.${cal[Calendar.YEAR]}"
                }.fold(StringArrayList()) { acc, s -> acc.apply { add(s) } }
            }.toTypedArray()
        } catch (e: Exception) {
            fail("Setup failed: $e")
        }
        executor.shutdown()
    }

    @Test
    fun dateValues() {
        val expectedDateValues = generateSequence { "15.11.2015" }.take(expectedCounterDateValues).toList()
        createdDateValues.forEach { assertEquals(expectedDateValues, it) }
    }

    @Test
    fun `counter dateValues`() = result.forEach { assertEquals(expectedCounterDateValues, it.dateList.size) }

    @Test
    fun `counter exceptions`() = result.forEach { assertEquals(0, it.exceptionList.size) }
}