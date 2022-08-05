import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.util.concurrent.Callable

class DateFormatCallable(inDateFormat: String, private val dateValue: String) : Callable<Result> {
    private val log = LoggerFactory.getLogger(javaClass)
    private val df = ThreadLocal.withInitial { SimpleDateFormat(inDateFormat) }
    override fun call(): Result {
        log.info("{} started executing...", Thread.currentThread())
        val result = Result()
        repeat(5) {
            try {
                result.dateList.add(df.get().parse(dateValue))
            } catch (e: Exception) {
                result.exceptionList.add("${e.javaClass}: ${e.localizedMessage}")
            }
        }
        log.info("{} finished processing part of the thread", Thread.currentThread())
        return result
    }
}