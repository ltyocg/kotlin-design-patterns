package com.ltyocg.aggregator.microservices

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class AggregatorTest {
    @InjectMocks
    private lateinit var aggregator: Aggregator

    @Mock
    private lateinit var informationClient: ProductInformationClient

    @Mock
    private lateinit var inventoryClient: ProductInventoryClient

    @BeforeTest
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test get product`() {
        val title = "The Product Title."
        val productInventories = 5
        whenever(informationClient.productTitle).thenReturn(title)
        whenever(inventoryClient.productInventories).thenReturn(5)
        val testProduct = aggregator.getProduct()
        assertEquals(title, testProduct.title)
        assertEquals(productInventories, testProduct.productInventories)
    }
}