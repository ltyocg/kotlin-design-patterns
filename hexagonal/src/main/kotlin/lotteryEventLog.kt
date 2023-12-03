import com.mongodb.MongoClient
import domain.PlayerDetails
import io.github.oshai.kotlinlogging.KotlinLogging
import org.bson.Document

interface LotteryEventLog {
    fun ticketSubmitted(details: PlayerDetails)
    fun ticketSubmitError(details: PlayerDetails)
    fun ticketDidNotWin(details: PlayerDetails)
    fun ticketWon(details: PlayerDetails, prizeAmount: Int)
    fun prizeError(details: PlayerDetails, prizeAmount: Int)
}

class MongoEventLog(dbName: String, eventsCollectionName: String) : LotteryEventLog {
    companion object {
        private const val DEFAULT_DB = "lotteryDB"
        private const val DEFAULT_EVENTS_COLLECTION = "events"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
        const val MESSAGE = "message"
    }

    constructor() : this(DEFAULT_DB, DEFAULT_EVENTS_COLLECTION)

    val mongoClient = MongoClient(System.getProperty("mongo-host"), System.getProperty("mongo-port").toInt())
    val database = mongoClient.getDatabase(dbName)
    val eventsCollection = database.getCollection(eventsCollectionName)
    private val stdOutEventLog = StdOutEventLog()

    override fun ticketSubmitted(details: PlayerDetails) {
        insertOne(details, "Lottery ticket was submitted and bank account was charged for 3 credits.")
        stdOutEventLog.ticketSubmitted(details)
    }

    override fun ticketSubmitError(details: PlayerDetails) {
        insertOne(details, "Lottery ticket could not be submitted because lack of funds.")
        stdOutEventLog.ticketSubmitError(details)
    }

    override fun ticketDidNotWin(details: PlayerDetails) {
        insertOne(details, "Lottery ticket was checked and unfortunately did not win this time.")
        stdOutEventLog.ticketDidNotWin(details)
    }

    override fun ticketWon(details: PlayerDetails, prizeAmount: Int) {
        insertOne(details, "Lottery ticket won! The bank account was deposited with $prizeAmount credits.")
        stdOutEventLog.ticketWon(details, prizeAmount)
    }

    override fun prizeError(details: PlayerDetails, prizeAmount: Int) {
        insertOne(details, "Lottery ticket won! Unfortunately the bank credit transfer of $prizeAmount failed.")
        stdOutEventLog.prizeError(details, prizeAmount)
    }

    private fun insertOne(details: PlayerDetails, message: String) {
        eventsCollection.insertOne(Document(EMAIL, details.email).apply {
            put(PHONE, details.phoneNumber)
            put("bank", details.bankAccount)
            put(MESSAGE, message)
        })
    }
}

class StdOutEventLog : LotteryEventLog {
    private val logger = KotlinLogging.logger {}
    override fun ticketSubmitted(details: PlayerDetails) =
        logger.info { "Lottery ticket for ${details.email} was submitted. Bank account ${details.bankAccount} was charged for 3 credits." }

    override fun ticketSubmitError(details: PlayerDetails) =
        logger.error { "Lottery ticket for ${details.email} could not be submitted because the credit transfer of 3 credits failed." }

    override fun ticketDidNotWin(details: PlayerDetails) =
        logger.info { "Lottery ticket for ${details.email} was checked and unfortunately did not win this time." }

    override fun ticketWon(details: PlayerDetails, prizeAmount: Int) =
        logger.info { "Lottery ticket for ${details.email} has won! The bank account ${details.bankAccount} was deposited with $prizeAmount credits." }

    override fun prizeError(details: PlayerDetails, prizeAmount: Int) =
        logger.error { "Lottery ticket for ${details.email} has won! Unfortunately the bank credit transfer of $prizeAmount failed." }
}
