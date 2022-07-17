import java.security.InvalidParameterException

class BarCustomer(
    val name: String,
    val allowedCallsPerSecond: Int,
    callsCount: CallsCount
) {
    init {
        if (allowedCallsPerSecond < 0) throw InvalidParameterException("Number of calls less than 0 not allowed")
        callsCount.addTenant(name)
    }
}