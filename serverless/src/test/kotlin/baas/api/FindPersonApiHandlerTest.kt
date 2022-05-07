package baas.api

import baas.model.Person
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import kotlin.test.BeforeTest
import kotlin.test.Test

class FindPersonApiHandlerTest {
    private lateinit var findPersonApiHandler: FindPersonApiHandler

    @Mock
    private lateinit var dynamoDbMapper: DynamoDBMapper

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        findPersonApiHandler = FindPersonApiHandler()
        findPersonApiHandler.dynamoDbMapper = dynamoDbMapper
    }

    @Test
    fun handleRequest() {
        findPersonApiHandler.handleRequest(APIGatewayProxyRequestEvent().withPathParameters(mapOf("id" to "37e7a1fe-3544-473d-b764-18128f02d72d")), mock())
        verify(dynamoDbMapper, times(1)).load(Person::class.java, "37e7a1fe-3544-473d-b764-18128f02d72d")
    }
}