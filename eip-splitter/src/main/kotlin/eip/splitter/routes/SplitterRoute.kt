package eip.splitter.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class SplitterRoute : RouteBuilder() {
    override fun configure() {
        from("{{entry}}").split().body().to("{{endpoint}}")
    }
}