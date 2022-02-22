package eip.splitter.routes

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

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SplitterRouteTest::class])
@ActiveProfiles("test")
@EnableAutoConfiguration
@ComponentScan
class SplitterRouteTest {
    @EndpointInject(uri = "{{entry}}")
    private lateinit var entry: ProducerTemplate

    @EndpointInject(uri = "{{endpoint}}")
    private lateinit var endpoint: MockEndpoint

    @Test
    fun `test splitter`() {
        arrayOf("TEST1", "TEST2", "TEST3").forEach { entry.sendBody(it) }
        endpoint.expectedMessageCount(3)
        endpoint.assertIsSatisfied()
    }
}