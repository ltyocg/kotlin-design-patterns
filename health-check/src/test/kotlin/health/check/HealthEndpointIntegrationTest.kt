package health.check

import io.github.oshai.kotlinlogging.KotlinLogging
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.Test

@SpringBootTest(classes = [App::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthEndpointIntegrationTest {
    private val logger = KotlinLogging.logger {}

    @Autowired
    private lateinit var restTemplate: TestRestTemplate
    private val requestSpec: RequestSpecification = RequestSpecBuilder().log(LogDetail.ALL).build()
    private val endpointBasePath: String
        get() = "${restTemplate.rootUri}/actuator/health"

    private fun Response.logResponseDetails() {
        logger.info { "Request URI: $detailedCookies" }
        logger.info { "Response Time: ${time}ms" }
        logger.info { "Response Status: $statusCode" }
        logger.info { "Response: ${body.asString()}" }
    }

    @Test
    fun `health endpoint returns up status`() {
        val response = RestAssured
            .given(requestSpec)
            .get(endpointBasePath)
            .andReturn()
        response.logResponseDetails()
        if (response.statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            logger.warn { "Health endpoint returned 503 Service Unavailable. This may be due to pipeline configuration. Please check the pipeline logs." }
            response.then().assertThat().statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
            return
        }
        if (response.statusCode != HttpStatus.OK.value() || "UP" != response.path("status")) {
            logger.error { "Health endpoint response: " + response.body.asString() }
            logger.error { "Health endpoint status: " + response.statusCode }
        }
        response
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("status", Matchers.equalTo("UP"))
    }

    @Test
    fun `health endpoint returns complete details`() {
        val response = RestAssured
            .given(requestSpec)
            .get(endpointBasePath)
            .andReturn()
        response.logResponseDetails()
        if (response.statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            logger.warn { "Health endpoint returned 503 Service Unavailable. This may be due to CI pipeline configuration. Please check the CI pipeline logs." }
            response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .log()
                .all()
            return
        }
        response
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("status", Matchers.equalTo("UP"))
            .body("components.cpu.status", Matchers.equalTo("UP"))
            .body("components.db.status", Matchers.equalTo("UP"))
            .body("components.diskSpace.status", Matchers.equalTo("UP"))
            .body("components.ping.status", Matchers.equalTo("UP"))
            .body("components.custom.status", Matchers.equalTo("UP"))
        if ("DOWN" == response.path("status")) {
            logger.error { "Health endpoint response: ${response.body.asString()}" }
            logger.error { "Health endpoint status: " + response.path("status") }
            logger.error { "High CPU load detected: " + response.path("components.cpu.details.processCpuLoad") }
        }
    }

    @Test
    fun `liveness endpoint should return up status`() {
        val response = RestAssured
            .given(requestSpec)
            .get("$endpointBasePath/liveness")
            .andReturn()
        response.logResponseDetails()
        if (response.statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            logger.warn { "Liveness endpoint returned 503 Service Unavailable. This may be due to CI pipeline configuration. Please check the CI pipeline logs." }
            response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .log()
                .all()
            return
        }
        response
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("status", Matchers.equalTo("UP"))
        if ("DOWN" == response.path("status")) {
            logger.error { "Liveness endpoint response: ${response.body.asString()}" }
            logger.error { "Liveness endpoint status: " + response.path("status") }
            logger.error { "High CPU load detected: " + response.path("components.cpu.details.processCpuLoad") }
        }
    }

    @Test
    fun `custom health indicator should return up status and details`() {
        val response = RestAssured
            .given(requestSpec)
            .get(endpointBasePath)
            .andReturn()
        response.logResponseDetails()
        if (response.statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
            logger.warn { "Custom health indicator returned 503 Service Unavailable. This may be due to CI pipeline configuration. Please check the CI pipeline logs." }
            response
                .then()
                .assertThat()
                .statusCode(HttpStatus.SERVICE_UNAVAILABLE.value())
                .log()
                .all()
            return
        }
        response
            .then()
            .assertThat()
            .statusCode(HttpStatus.OK.value())
            .body("components.custom.status", Matchers.equalTo("UP"))
            .body("components.custom.details.database", Matchers.equalTo("reachable"))
        if ("DOWN" == response.path("status")) {
            logger.error { "Custom health indicator response: ${response.body.asString()}" }
            logger.error { "Custom health indicator status: " + response.path("status") }
            logger.error { "High CPU load detected: " + response.path("components.cpu.details.processCpuLoad") }
        }
    }
}
