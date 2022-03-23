import domain.LotteryAdministration
import org.springframework.beans.factory.getBean

fun main() {
    val administration = lotteryTestingContext.getBean<LotteryAdministration>()
    administration.resetLottery()
    SampleData.submitTickets(lotteryTestingContext.getBean(), 20)
    administration.performLottery()
}