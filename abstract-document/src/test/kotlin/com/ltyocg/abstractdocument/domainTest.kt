package com.ltyocg.abstractdocument

import com.ltyocg.abstractdocument.domain.Car
import com.ltyocg.abstractdocument.domain.Part
import com.ltyocg.abstractdocument.domain.enums.Property
import kotlin.test.Test
import kotlin.test.assertEquals

private const val TEST_PART_TYPE = "test-part-type"
private const val TEST_PART_MODEL = "test-part-model"
private const val TEST_PART_PRICE = 0L
private const val TEST_CAR_MODEL = "test-car-model"
private const val TEST_CAR_PRICE = 1L

class DomainTest {
    @Test
    fun `should construct part`() {
        val part = Part(
            mapOf(
                Property.TYPE.name to TEST_PART_TYPE,
                Property.MODEL.name to TEST_PART_MODEL,
                Property.PRICE.name to TEST_PART_PRICE
            )
        )
        assertEquals(TEST_PART_TYPE, part.type)
        assertEquals(TEST_PART_MODEL, part.model)
        assertEquals(TEST_PART_PRICE, part.price)
    }

    @Test
    fun `should construct car`() {
        val car = Car(
            mapOf(
                Property.MODEL.name to TEST_CAR_MODEL,
                Property.PRICE.name to TEST_CAR_PRICE,
                Property.PARTS.name to listOf<Map<String, Any?>>(emptyMap(), emptyMap())
            )
        )
        assertEquals(TEST_CAR_MODEL, car.model)
        assertEquals(TEST_CAR_PRICE, car.price)
        assertEquals(2, car.parts.count())
    }
}