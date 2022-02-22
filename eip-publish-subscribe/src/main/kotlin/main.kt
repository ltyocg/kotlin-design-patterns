package com.ltyocg.eip.publish.subscribe

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    with(DefaultCamelContext()) {
        addRoutes(object : RouteBuilder() {
            override fun configure() {
                from("direct:origin").multicast().to("mock:foo", "mock:bar", "stream:out")
            }
        })
        start()
        routes.forEach { log.info(it.toString()) }
        createProducerTemplate().sendBody("direct:origin", "Hello from origin")
        stop()
    }
}