private const val JNDI_SERVICE_A = "jndi/serviceA"
private const val JNDI_SERVICE_B = "jndi/serviceB"
fun main() = arrayOf(JNDI_SERVICE_A, JNDI_SERVICE_B, JNDI_SERVICE_A, JNDI_SERVICE_A)
    .map(ServiceLocator::getService)
    .forEach { it!!.execute() }