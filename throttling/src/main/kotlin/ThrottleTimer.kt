import java.util.*

class ThrottleTimer(private val throttlePeriod: Int, private val callsCount: CallsCount) : () -> Unit {
    override fun invoke() = Timer(true).schedule(object : TimerTask() {
        override fun run() = callsCount.reset()
    }, 0, throttlePeriod.toLong())
}