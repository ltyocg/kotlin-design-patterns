package corruption.system.legacy

data class LegacyOrder(
    val id: String,
    val customer: String,
    val item: String,
    val qty: Int,
    val price: Int
)
