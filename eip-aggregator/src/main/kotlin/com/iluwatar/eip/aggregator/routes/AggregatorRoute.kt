package com.iluwatar.eip.aggregator.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class AggregatorRoute(private val aggregator: MessageAggregationStrategy) : RouteBuilder() {
    override fun configure() {
        from("{{entry}}")
            .aggregate(constant(true), aggregator)
            .completionSize(3)
            .completionInterval(2000)
            .to("{{endpoint}}")
    }
}