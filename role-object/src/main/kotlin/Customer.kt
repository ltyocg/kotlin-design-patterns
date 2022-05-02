import kotlin.reflect.KClass

abstract class Customer {
    abstract fun addRole(role: Role): Boolean
    abstract fun hasRole(role: Role): Boolean
    abstract fun remRole(role: Role): Boolean
    abstract fun <T : Customer> getRole(role: Role, expectedRole: KClass<T>): T?

    companion object {
        fun newCustomer(vararg role: Role): Customer = CustomerCore().apply { role.forEach(::addRole) }
    }
}