abstract class CustomerRole : CustomerCore()

class BorrowerRole : CustomerRole() {
    var name: String? = null
    fun borrow(): String = "Borrower $name wants to get some money."
}

class InvestorRole : CustomerRole() {
    var name: String? = null
    var amountToInvest: Long = 0
    fun invest(): String = "Investor $name has invested $amountToInvest dollars"
}