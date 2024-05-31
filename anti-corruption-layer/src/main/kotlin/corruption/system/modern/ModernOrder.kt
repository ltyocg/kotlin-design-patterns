package corruption.system.modern

data class ModernOrder(
    val id: String,
    val customer: Customer,
    val shipment: Shipment,
    val extra: String
)
