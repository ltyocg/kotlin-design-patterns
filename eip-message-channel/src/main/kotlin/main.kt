package com.ltyocg.eip.message.channel

import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    with(DefaultCamelContext()) {
        addRoutes(object : RouteBuilder() {
            override fun configure() {
                from("stream:in").to("direct:greetings")
                from("direct:greetings").to("stream:out")
            }
        })
        start()
        routes.forEach { log.info(it.toString()) }
        stop()
    }
}