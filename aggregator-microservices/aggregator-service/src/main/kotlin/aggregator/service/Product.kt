package aggregator.service

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val title: String,
    val productInventories: Int
)
