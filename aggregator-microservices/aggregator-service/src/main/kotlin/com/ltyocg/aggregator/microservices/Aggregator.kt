package com.ltyocg.aggregator.microservices

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Aggregator(
    private val informationClient: ProductInformationClient,
    private val inventoryClient: ProductInventoryClient
) {
    @GetMapping("/product")
    fun getProduct(): Product = Product(
        informationClient.productTitle ?: "Error: Fetching Product Title Failed",
        inventoryClient.productInventories ?: -1
    )
}