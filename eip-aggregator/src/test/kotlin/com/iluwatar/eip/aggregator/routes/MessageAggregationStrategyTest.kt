package com.iluwatar.eip.aggregator.routes

import org.apache.camel.CamelContext
import org.apache.camel.support.DefaultExchange
import kotlin.test.Test
import kotlin.test.assertEquals

class MessageAggregationStrategyTest {
    @Test
    fun `test aggregate`() {
        assertEquals("TEST1;TEST2", MessageAggregationStrategy().aggregate(
            DefaultExchange(null as CamelContext?).apply { getIn().body = "TEST1" },
            DefaultExchange(null as CamelContext?).apply { getIn().body = "TEST2" }
        ).getIn().body as String)
    }

    @Test
    fun `test aggregate old null`() {
        val newExchange = DefaultExchange(null as CamelContext?).apply { getIn().body = "TEST2" }
        val output = MessageAggregationStrategy().aggregate(null, newExchange)
        assertEquals(newExchange, output)
        assertEquals("TEST2", output.getIn().body as String)
    }
}