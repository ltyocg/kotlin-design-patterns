import domain.LotteryAdministration
import domain.LotteryService
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans

val lotteryContext = StaticApplicationContext().apply {
    beans {
        bean<MongoTicketRepository>()
        bean<MongoEventLog>()
        bean<MongoBank>()
        bean<LotteryService>()
        bean<LotteryAdministration>()
    }.initialize(this)
}
val lotteryTestingContext = StaticApplicationContext().apply {
    beans {
        bean<InMemoryTicketRepository>()
        bean<StdOutEventLog>()
        bean<InMemoryBank>()
        bean<LotteryService>()
        bean<LotteryAdministration>()
    }.initialize(this)
}