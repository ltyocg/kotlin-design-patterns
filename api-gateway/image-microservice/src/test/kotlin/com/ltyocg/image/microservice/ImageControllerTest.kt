package com.ltyocg.image.microservice

import kotlin.test.Test
import kotlin.test.assertEquals

class ImageControllerTest {
    @Test
    fun `test get image path`() {
        assertEquals("/product-image.png", ImageController().getImagePath())
    }
}