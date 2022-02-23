interface Filter {
    fun execute(order: Order): String
    var next: Filter?
    val last: Filter
}

abstract class AbstractFilter(override var next: Filter? = null) : Filter {
    override fun execute(order: Order): String = next?.execute(order) ?: ""
    override val last: Filter
        get() {
            var last: Filter = this
            while (true) last = last.next ?: return last
        }
}

class AddressFilter : AbstractFilter() {
    override fun execute(order: Order): String {
        val result = super.execute(order)
        return if (order.address.isNullOrEmpty()) "${result}Invalid address! "
        else result
    }
}

class ContactFilter : AbstractFilter() {
    override fun execute(order: Order): String {
        val result = super.execute(order)
        return if (order.contactNumber.let { it == null || it.matches(Regex(".*[^\\d]+.*")) || it.length != 11 }) "${result}Invalid contact number! "
        else result
    }
}

class DepositFilter : AbstractFilter() {
    override fun execute(order: Order): String {
        val result = super.execute(order)
        return if (order.depositNumber.isNullOrEmpty()) "${result}Invalid deposit number! "
        else result
    }
}

class NameFilter : AbstractFilter() {
    override fun execute(order: Order): String {
        val result = super.execute(order)
        return if (order.name.let { it.isNullOrEmpty() || it.matches(Regex(".*[^\\w|\\s]+.*")) }) "${result}Invalid name! "
        else result
    }
}

class OrderFilter : AbstractFilter() {
    override fun execute(order: Order): String {
        val result = super.execute(order)
        return if (order.orderItem.isNullOrEmpty()) "${result}Invalid order! "
        else result
    }
}
