data class Order(
    val id: Int = 0,
    val item: String,
    val orderedBy: String,
    val shippingAddress: ShippingAddress
)