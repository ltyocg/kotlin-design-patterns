import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.time.Duration
import java.util.*

private val logger = KotlinLogging.logger {}
private const val PROP_FILE_NAME = "config.properties"
private var interactiveMode = false

class App

fun main() {
    val prop = Properties()
    val inputStream = App::class.java.classLoader.getResourceAsStream(PROP_FILE_NAME)
    if (inputStream != null) {
        try {
            prop.load(inputStream)
        } catch (e: IOException) {
            logger.error(e) { "$PROP_FILE_NAME was not found. Defaulting to non-interactive mode." }
        }
        if (prop.getProperty("INTERACTIVE_MODE").equals("YES", ignoreCase = true)) interactiveMode = true
    }
    if (interactiveMode) runInteractiveMode() else quickRun()
}

fun quickRun() {
    val eventManager = EventManager()
    try {
        val asyncEventId = eventManager.createAsync(Duration.ofSeconds(60))
        logger.info { "Async Event [$asyncEventId] has been created." }
        eventManager.start(asyncEventId)
        logger.info { "Async Event [$asyncEventId] has been started." }
        val syncEventId = eventManager.create(Duration.ofSeconds(60))
        logger.info { "Sync Event [$syncEventId] has been created." }
        eventManager.start(syncEventId)
        logger.info { "Sync Event [$syncEventId] has been started." }
        eventManager.status(asyncEventId)
        eventManager.status(syncEventId)
        eventManager.cancel(asyncEventId)
        logger.info { "Async Event [$asyncEventId] has been stopped." }
        eventManager.cancel(syncEventId)
        logger.info { "Sync Event [$syncEventId] has been stopped." }
    } catch (e: MaxNumOfEventsAllowedException) {
        logger.error { e.message }
    } catch (e: LongRunningEventException) {
        logger.error { e.message }
    } catch (e: EventDoesNotExistException) {
        logger.error { e.message }
    } catch (e: InvalidOperationException) {
        logger.error { e.message }
    }
}

private fun runInteractiveMode() {
    val eventManager = EventManager()
    Scanner(System.`in`).use {
        var option = -1
        while (option != 4) {
            logger.info { "Hello. Would you like to boil some eggs?" }
            logger.info {
                """
                      (1) BOIL AN EGG
                      (2) STOP BOILING THIS EGG
                      (3) HOW ARE MY EGGS?
                      (4) EXIT
                      
                      """.trimIndent()
            }
            logger.info { "Choose [1,2,3,4]: " }
            option = it.nextInt()
            when (option) {
                1 -> processOption1(eventManager, it)
                2 -> processOption2(eventManager, it)
                3 -> processOption3(eventManager, it)
                4 -> eventManager.shutdown()
            }
        }
    }
}

private fun processOption3(eventManager: EventManager, s: Scanner) {
    s.nextLine()
    logger.info { "Just one egg (O) OR all of them (A) ?: " }
    val eggChoice = s.nextLine()
    when {
        eggChoice.equals("O", ignoreCase = true) -> {
            logger.info { "Which egg?: " }
            val eventId = s.nextInt()
            try {
                eventManager.status(eventId)
            } catch (e: EventDoesNotExistException) {
                logger.error { e.message }
            }
        }

        eggChoice.equals("A", ignoreCase = true) -> eventManager.statusOfAllEvents()
    }
}

private fun processOption2(eventManager: EventManager, s: Scanner) {
    logger.info { "Which egg?: " }
    val eventId = s.nextInt()
    try {
        eventManager.cancel(eventId)
        logger.info { "Egg [$eventId] is removed from boiler." }
    } catch (e: EventDoesNotExistException) {
        logger.error { e.message }
    }
}

private fun processOption1(eventManager: EventManager, s: Scanner) {
    s.nextLine()
    logger.info { "Boil multiple eggs at once (A) or boil them one-by-one (S)?: " }
    val eventType = s.nextLine()
    logger.info { "How long should this egg be boiled for (in seconds)?: " }
    val eventTime = Duration.ofSeconds(s.nextInt().toLong())
    when {
        eventType.equals("A", ignoreCase = true) -> try {
            val eventId = eventManager.createAsync(eventTime)
            eventManager.start(eventId)
            logger.info { "Egg [$eventId] is being boiled." }
        } catch (e: MaxNumOfEventsAllowedException) {
            logger.error { e.message }
        } catch (e: LongRunningEventException) {
            logger.error { e.message }
        } catch (e: EventDoesNotExistException) {
            logger.error { e.message }
        }

        eventType.equals("S", ignoreCase = true) -> try {
            val eventId = eventManager.create(eventTime)
            eventManager.start(eventId)
            logger.info { "Egg [$eventId] is being boiled." }
        } catch (e: MaxNumOfEventsAllowedException) {
            logger.error { e.message }
        } catch (e: InvalidOperationException) {
            logger.error { e.message }
        } catch (e: LongRunningEventException) {
            logger.error { e.message }
        } catch (e: EventDoesNotExistException) {
            logger.error { e.message }
        }

        else -> logger.info { "Unknown event type." }
    }
}
