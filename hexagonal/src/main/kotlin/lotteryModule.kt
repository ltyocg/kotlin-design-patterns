import com.google.inject.AbstractModule

class LotteryModule : AbstractModule() {
    override fun configure() {
        bind(LotteryTicketRepository::class.java).to(MongoTicketRepository::class.java)
        bind(LotteryEventLog::class.java).to(MongoEventLog::class.java)
        bind(WireTransfers::class.java).to(MongoBank::class.java)
    }
}

class LotteryTestingModule : AbstractModule() {
    override fun configure() {
        bind(LotteryTicketRepository::class.java).to(InMemoryTicketRepository::class.java)
        bind(LotteryEventLog::class.java).to(StdOutEventLog::class.java)
        bind(WireTransfers::class.java).to(InMemoryBank::class.java)
    }
}