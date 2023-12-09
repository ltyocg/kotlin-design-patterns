import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    val customer = Customer.newCustomer(Role.Borrower, Role.Investor)
    logger.info { " the new customer created : $customer" }
    val hasBorrowerRole = customer.hasRole(Role.Borrower)
    logger.info { " customer has a borrowed role - $hasBorrowerRole" }
    val hasInvestorRole = customer.hasRole(Role.Investor)
    logger.info { " customer has an investor role - $hasInvestorRole" }
    customer.getRole(Role.Investor, InvestorRole::class)?.also {
        it.amountToInvest = 1000
        it.name = "Billy"
    }
    customer.getRole(Role.Borrower, BorrowerRole::class)?.also {
        it.name = "Johny"
    }
    customer.getRole(Role.Investor, InvestorRole::class)
        ?.invest()
        ?.let { logger.info { it } }
    customer.getRole(Role.Borrower, BorrowerRole::class)
        ?.borrow()
        ?.let { logger.info { it } }
}
