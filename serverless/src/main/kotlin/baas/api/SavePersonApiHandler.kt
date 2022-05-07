package baas.api

import baas.model.Person
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class SavePersonApiHandler : AbstractDynamoDbHandler<Person?>(), RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val log = LoggerFactory.getLogger(SavePersonApiHandler::class.java)
    override fun handleRequest(req: APIGatewayProxyRequestEvent, ctx: Context): APIGatewayProxyResponseEvent = try {
        val person = Json.decodeFromString<Person>(req.body)
        dynamoDbMapper.save(person)
        apiGatewayProxyResponseEvent(201, person)
    } catch (e: SerializationException) {
        log.error("unable to parse body", e)
        apiGatewayProxyResponseEvent(400, null as Person?)
    }
}