import org.slf4j.LoggerFactory
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.Future

private val log = LoggerFactory.getLogger("main")
fun main() {
    val callableDf = DateFormatCallable("dd/MM/yyyy", "15/12/2015")
    val executor = Executors.newCachedThreadPool()
    try {
        var counterDateValues = 0
        var counterExceptions = 0
        generateSequence { executor.submit(callableDf) }
            .take(4)
            .map(Future<Result>::get)
            .forEach {
                counterDateValues += printAndCountDates(it)
                counterExceptions += printAndCountExceptions(it)
            }
        log.info("The List dateList contains {} date values", counterDateValues)
        log.info("The List exceptionList contains {} exceptions", counterExceptions)
    } catch (e: Exception) {
        log.info("Abnormal end of program. Program throws exception: {}", e.toString())
    }
    executor.shutdown()
}

private fun printAndCountDates(res: Result): Int {
    res.dateList.forEach {
        val cal = Calendar.getInstance().apply { time = it }
        log.info("{}.{}.{}", cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
    }
    return res.dateList.size
}

private fun printAndCountExceptions(res: Result): Int {
    res.exceptionList.forEach(log::info)
    return res.exceptionList.size
}