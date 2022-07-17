import java.util.concurrent.atomic.AtomicInteger

abstract class Task(val timeMs: Int) {
    val id = ID_GENERATOR.incrementAndGet()

    private companion object {
        private val ID_GENERATOR = AtomicInteger()
    }

    override fun toString(): String = "id=$id timeMs=$timeMs"
}

class CoffeeMakingTask(numCups: Int) : Task(numCups * TIME_PER_CUP) {
    private companion object {
        private const val TIME_PER_CUP = 100
    }

    override fun toString(): String = "${javaClass.simpleName} ${super.toString()}"
}

class PotatoPeelingTask(numPotatoes: Int) : Task(numPotatoes * TIME_PER_POTATO) {
    private companion object {
        private const val TIME_PER_POTATO = 200
    }

    override fun toString(): String = "${javaClass.simpleName} ${super.toString()}"
}