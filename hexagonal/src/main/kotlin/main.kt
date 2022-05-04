import domain.LotteryAdministration
import domain.LotteryService
import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans

val lotteryContext = StaticApplicationContext().apply {
    beans {
        bean<InMemoryTicketRepository>()
        bean<StdOutEventLog>()
        bean<InMemoryBank>()
        bean<LotteryService>()
        bean<LotteryAdministration>()
    }.initialize(this)
}

fun main() {
    val administration = lotteryContext.getBean<LotteryAdministration>()
    administration.resetLottery()
    SampleData.submitTickets(lotteryContext.getBean(), 20)
    administration.performLottery()
}