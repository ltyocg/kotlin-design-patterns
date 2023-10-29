import java.util.*

class FindCustomer(
    private val customerId: String,
    vararg errors: BusinessException
) : BusinessOperation<String> {
    private val errors = ArrayDeque(errors.toList())
    override fun perform(): String =
        if (errors.isEmpty()) customerId
        else throw errors.pop()
}
