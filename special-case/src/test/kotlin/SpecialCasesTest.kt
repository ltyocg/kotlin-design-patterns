import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.read.ListAppender
import org.slf4j.LoggerFactory
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SpecialCasesTest {
    init {
        Db.seedUser("ignite1771", 1000.0)
        Db.seedItem("computer", 800.0)
        Db.seedItem("car", 20000.0)
    }

    @BeforeTest
    fun beforeEach() {
        MaintenanceLock.lock = false
    }

    @Test
    fun `test DownForMaintenance`() {
        val listAppender = addListAppender<DownForMaintenance>()
        MaintenanceLock.lock = true
        ApplicationServices.loggedInUserPurchase(null, null).show()
        val loggingEventList = listAppender.list
        assertEquals("Down for maintenance", loggingEventList[0].formattedMessage)
        assertEquals(Level.INFO, loggingEventList[0].level)
    }

    @Test
    fun `test InvalidUser`() {
        val listAppender = addListAppender<InvalidUser>()
        ApplicationServices.loggedInUserPurchase("a", null).show()
        val loggingEventList = listAppender.list
        assertEquals("Invalid user: a", loggingEventList[0].formattedMessage)
        assertEquals(Level.INFO, loggingEventList[0].level)
    }

    @Test
    fun `test OutOfStock`() {
        val listAppender = addListAppender<OutOfStock>()
        ApplicationServices.loggedInUserPurchase("ignite1771", "tv").show()
        val loggingEventList = listAppender.list
        assertEquals("Out of stock: tv for user = ignite1771 to buy", loggingEventList[0].formattedMessage)
        assertEquals(Level.INFO, loggingEventList[0].level)
    }

    @Test
    fun `test InsufficientFunds`() {
        val listAppender = addListAppender<InsufficientFunds>()
        ApplicationServices.loggedInUserPurchase("ignite1771", "car").show()
        val loggingEventList = listAppender.list
        assertEquals("Insufficient funds: 1000.0 of user: ignite1771 for buying item: car", loggingEventList[0].formattedMessage)
        assertEquals(Level.INFO, loggingEventList[0].level)
    }

    @Test
    fun `test ReceiptDto`() {
        val listAppender = addListAppender<ReceiptDto>()
        ApplicationServices.loggedInUserPurchase("ignite1771", "computer").show()
        val loggingEventList = listAppender.list
        assertEquals("Receipt: 800.0 paid", loggingEventList[0].formattedMessage)
        assertEquals(Level.INFO, loggingEventList[0].level)
    }

    private inline fun <reified T> addListAppender(): ListAppender<ILoggingEvent> =
        ListAppender<ILoggingEvent>().also {
            it.start()
            (LoggerFactory.getLogger(T::class.java) as Logger).addAppender(it)
        }
}