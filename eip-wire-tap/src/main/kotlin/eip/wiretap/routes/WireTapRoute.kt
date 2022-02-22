package eip.wiretap.routes

import org.apache.camel.builder.RouteBuilder
import org.springframework.stereotype.Component

@Component
class WireTapRoute : RouteBuilder() {
    override fun configure() {
        from("{{entry}}").wireTap("direct:wireTap").to("{{endpoint}}")
        from("direct:wireTap").log("Message: \${body}").to("{{wireTapEndpoint}}")
    }
}