package eip.aggregator.routes

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
@SpringBootTest(classes = [AggregatorRouteTest::class])
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan
class AggregatorRouteTest {
    @EndpointInject(uri = "{{entry}}")
    private lateinit var entry: ProducerTemplate

    @EndpointInject(uri = "{{endpoint}}")
    private lateinit var endpoint: MockEndpoint

    @Test
    fun `test splitter`() {
        arrayOf("TEST1", "TEST2", "TEST3", "TEST4", "TEST5").forEach { entry.sendBody(it) }
        endpoint.expectedMessageCount(2)
        endpoint.assertIsSatisfied()
        assertEquals(3, (endpoint.receivedExchanges[0].getIn().body as String).split(";").size)
        assertEquals(2, (endpoint.receivedExchanges[1].getIn().body as String).split(";").size)
    }
}