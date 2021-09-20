package com.ltyocg.eip.wiretap.routes

import org.apache.camel.EndpointInject
import org.apache.camel.ProducerTemplate
import org.apache.camel.component.mock.MockEndpoint
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [WireTapRouteTest::class])
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan
class WireTapRouteTest {
    @EndpointInject(uri = "{{entry}}")
    private lateinit var entry: ProducerTemplate

    @EndpointInject(uri = "{{endpoint}}")
    private lateinit var endpoint: MockEndpoint

    @EndpointInject(uri = "{{wireTapEndpoint}}")
    private lateinit var wireTapEndpoint: MockEndpoint

    @Test
    fun `test wireTap`() {
        entry.sendBody("TEST")
        endpoint.expectedMessageCount(1)
        wireTapEndpoint.expectedMessageCount(1)
        endpoint.assertIsSatisfied()
        wireTapEndpoint.assertIsSatisfied()
        assertEquals("TEST", endpoint.exchanges[0].getIn().body)
        assertEquals("TEST", wireTapEndpoint.exchanges[0].getIn().body)
    }
}