package com.ltyocg.api.gateway

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ApiGatewayTest {
    @InjectMocks
    private lateinit var apiGateway: ApiGateway

    @Mock
    private lateinit var imageClient: ImageClient

    @Mock
    private lateinit var priceClient: PriceClient

    @BeforeTest
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test getProductDesktop`() {
        val imagePath = "/product-image.png"
        val price = "20"
        whenever(imageClient.imagePath).thenReturn(imagePath)
        whenever(priceClient.price).thenReturn(price)
        val desktopProduct = apiGateway.getProductDesktop()
        assertEquals(imagePath, desktopProduct.imagePath)
        assertEquals(price, desktopProduct.price)
    }

    @Test
    fun `test getProductMobile`() {
        val price = "20"
        whenever(priceClient.price).thenReturn(price)
        val productMobile = apiGateway.getProductMobile()
        assertEquals(price, productMobile.price)
    }
}