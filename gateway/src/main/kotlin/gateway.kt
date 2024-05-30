import io.github.oshai.kotlinlogging.KotlinLogging

interface Gateway {
    fun execute()
}

class ExternalServiceA : Gateway {
    private val logger = KotlinLogging.logger {}
    override fun execute() {
        logger.info { "Executing Service A" }
        Thread.sleep(1000)
    }
}

class ExternalServiceB : Gateway {
    private val logger = KotlinLogging.logger {}
    override fun execute() {
        logger.info { "Executing Service B" }
        Thread.sleep(1000)
    }
}

class ExternalServiceC : Gateway {
    private val logger = KotlinLogging.logger {}
    override fun execute() {
        logger.info { "Executing Service C" }
        Thread.sleep(1000)
    }

    fun error(): Nothing = error("Service C encountered an error")
}
