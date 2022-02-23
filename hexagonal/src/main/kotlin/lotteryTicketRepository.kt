import com.mongodb.MongoClient
import domain.LotteryNumbers
import domain.LotteryTicket
import domain.LotteryTicketId
import domain.PlayerDetails
import org.bson.Document

interface LotteryTicketRepository {
    fun findById(id: LotteryTicketId): LotteryTicket?
    fun save(ticket: LotteryTicket): LotteryTicketId
    fun findAll(): Map<LotteryTicketId, LotteryTicket>
    fun deleteAll()
}

class InMemoryTicketRepository : LotteryTicketRepository {
    companion object {
        private val tickets = mutableMapOf<LotteryTicketId, LotteryTicket>()
    }

    override fun findById(id: LotteryTicketId): LotteryTicket? = tickets[id]
    override fun save(ticket: LotteryTicket): LotteryTicketId = LotteryTicketId().also { tickets[it] = ticket }
    override fun findAll(): Map<LotteryTicketId, LotteryTicket> = tickets
    override fun deleteAll() {
        tickets.clear()
    }
}

class MongoTicketRepository(
    dbName: String,
    ticketsCollectionName: String,
    countersCollectionName: String
) : LotteryTicketRepository {
    companion object {
        private const val DEFAULT_DB = "lotteryDB"
        private const val DEFAULT_TICKETS_COLLECTION = "lotteryTickets"
        private const val DEFAULT_COUNTERS_COLLECTION = "counters"
        private const val TICKET_ID = "ticketId"
    }

    constructor() : this(DEFAULT_DB, DEFAULT_TICKETS_COLLECTION, DEFAULT_COUNTERS_COLLECTION)

    private val mongoClient = MongoClient(System.getProperty("mongo-host"), System.getProperty("mongo-port").toInt())
    private val database = mongoClient.getDatabase(dbName)
    val ticketsCollection = database.getCollection(ticketsCollectionName)
    val countersCollection = database.getCollection(countersCollectionName)

    init {
        if (countersCollection.countDocuments() <= 0) countersCollection.insertOne(Document("_id", TICKET_ID).append("seq", 1))
    }

    fun getNextId(): Int = countersCollection.findOneAndUpdate(Document("_id", TICKET_ID), Document("\$inc", Document("seq", 1)))!!.getInteger("seq")

    override fun findById(id: LotteryTicketId): LotteryTicket? = ticketsCollection
        .find(Document(TICKET_ID, id.id))
        .limit(1)
        .into(mutableListOf())
        .firstOrNull()
        ?.let(::docToTicket)

    override fun save(ticket: LotteryTicket): LotteryTicketId {
        val ticketId = getNextId()
        ticketsCollection.insertOne(Document(TICKET_ID, ticketId).apply {
            put("email", ticket.playerDetails.email)
            put("bank", ticket.playerDetails.bankAccount)
            put("phone", ticket.playerDetails.phoneNumber)
            put("numbers", ticket.lotteryNumbers.numbersAsString)
        })
        return LotteryTicketId(ticketId)
    }

    override fun findAll(): Map<LotteryTicketId, LotteryTicket> = ticketsCollection
        .find(Document())
        .into(mutableListOf())
        .map(::docToTicket)
        .associateBy(LotteryTicket::id)

    override fun deleteAll() {
        ticketsCollection.deleteMany(Document())
    }

    private fun docToTicket(doc: Document): LotteryTicket = LotteryTicket(
        LotteryTicketId(doc.getInteger(TICKET_ID)),
        PlayerDetails(doc.getString("email"), doc.getString("bank"), doc.getString("phone")),
        LotteryNumbers.create(doc.getString("numbers").splitToSequence(",").map(String::toInt).toSet())
    )
}