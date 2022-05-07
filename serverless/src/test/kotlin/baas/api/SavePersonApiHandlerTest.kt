package baas.api

import baas.model.Address
import baas.model.Person
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class SavePersonApiHandlerTest {
    private lateinit var savePersonApiHandler: SavePersonApiHandler

    @Mock
    private lateinit var dynamoDbMapper: DynamoDBMapper

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        savePersonApiHandler = SavePersonApiHandler()
        savePersonApiHandler.dynamoDbMapper = dynamoDbMapper
    }

    @Test
    fun `handleRequest save person successful`() {
        val person = Person(
            firstName = "Thor",
            lastName = "Odinson",
            address = Address(
                "1 Odin ln",
                city = "Asgard",
                state = "country of the Gods",
                zipCode = "00001"
            )
        )
        val apiGatewayProxyResponseEvent = savePersonApiHandler.handleRequest(apiGatewayProxyRequestEvent(Json.encodeToString(person)), mock())
        verify(dynamoDbMapper, times(1)).save(person)
        assertEquals(201, apiGatewayProxyResponseEvent.statusCode)
    }

    @Test
    fun `handleRequest save person exception`() =
        assertEquals(400, savePersonApiHandler.handleRequest(apiGatewayProxyRequestEvent("invalid sample request"), mock()).statusCode)

    private fun apiGatewayProxyRequestEvent(body: String): APIGatewayProxyRequestEvent = APIGatewayProxyRequestEvent().withBody(body)
}