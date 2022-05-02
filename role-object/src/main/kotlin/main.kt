import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    val customer = Customer.newCustomer(Role.Borrower, Role.Investor)
    log.info(" the new customer created : {}", customer)
    val hasBorrowerRole = customer.hasRole(Role.Borrower)
    log.info(" customer has a borrowed role - {}", hasBorrowerRole)
    val hasInvestorRole = customer.hasRole(Role.Investor)
    log.info(" customer has an investor role - {}", hasInvestorRole)
    customer.getRole(Role.Investor, InvestorRole::class)?.also {
        it.amountToInvest = 1000
        it.name = "Billy"
    }
    customer.getRole(Role.Borrower, BorrowerRole::class)?.also {
        it.name = "Johny"
    }
    customer.getRole(Role.Investor, InvestorRole::class)
        ?.invest()
        ?.let(log::info)
    customer.getRole(Role.Borrower, BorrowerRole::class)
        ?.borrow()
        ?.let(log::info)
}