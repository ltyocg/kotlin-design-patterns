import java.util.concurrent.ConcurrentHashMap

object CustomerRegistry {
    private val customerMap = ConcurrentHashMap<String, Customer>()
    fun addCustomer(customer: Customer): Customer? = customerMap.put(customer.id, customer)
    fun getCustomer(id: String): Customer? = customerMap[id]
}