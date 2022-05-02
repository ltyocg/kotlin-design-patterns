import java.util.*

class FindCustomer(
    private val customerId: String,
    vararg errors: BusinessException
) : BusinessOperation<String> {
    private val errors = ArrayDeque(listOf(*errors))
    override fun perform(): String {
        if (!errors.isEmpty()) throw errors.pop()
        return customerId
    }
}