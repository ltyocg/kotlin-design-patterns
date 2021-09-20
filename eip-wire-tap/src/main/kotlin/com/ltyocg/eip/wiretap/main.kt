package com.ltyocg.eip.wiretap

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.springframework.beans.factory.getBean
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class App

fun main(args: Array<String>) {
    val context = runApplication<App>(*args)
    val camelContext = context.getBean<CamelContext>()
    camelContext.addRoutes(object : RouteBuilder() {
        override fun configure() {
            from("{{endpoint}}").log("ENDPOINT: \${body}")
            from("{{wireTapEndpoint}}").log("WIRETAPPED ENDPOINT: \${body}")
        }
    })
    camelContext.createProducerTemplate().sendBody("{{entry}}", "Test message")
    SpringApplication.exit(context)
}