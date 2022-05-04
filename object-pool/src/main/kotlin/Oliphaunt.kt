import java.util.concurrent.atomic.AtomicInteger

class Oliphaunt {
    val id = counter.incrementAndGet()

    init {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    override fun toString(): String = "Oliphaunt id=$id"

    private companion object {
        private val counter = AtomicInteger(0)
    }
}