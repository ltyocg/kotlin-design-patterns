package com.ltyocg.api.gateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiGateway(
    private val imageClient: ImageClient,
    private val priceClient: PriceClient
) {
    @GetMapping("/desktop")
    fun getProductDesktop(): DesktopProduct = DesktopProduct(priceClient.price, imageClient.imagePath)

    @GetMapping("/mobile")
    fun getProductMobile(): MobileProduct = MobileProduct(priceClient.price)
}