import kotlin.reflect.KClass
import kotlin.reflect.cast

open class CustomerCore : Customer() {
    private val roles = mutableMapOf<Role, CustomerRole>()
    override fun addRole(role: Role): Boolean {
        val instance = role.instance()
        return if (instance != null) {
            roles[role] = instance
            true
        } else false
    }

    override fun hasRole(role: Role): Boolean = roles.containsKey(role)
    override fun remRole(role: Role): Boolean = roles.remove(role) != null
    override fun <T : Customer> getRole(role: Role, expectedRole: KClass<T>): T? = roles[role]
        ?.takeIf { expectedRole.isInstance(it) }
        ?.let(expectedRole::cast)

    override fun toString(): String = "Customer{roles=${roles.keys}}"
}