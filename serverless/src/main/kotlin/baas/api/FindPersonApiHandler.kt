package baas.api

import baas.model.Person
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import org.slf4j.LoggerFactory

class FindPersonApiHandler : AbstractDynamoDbHandler<Person>(), RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun handleRequest(req: APIGatewayProxyRequestEvent, ctx: Context): APIGatewayProxyResponseEvent {
        req.pathParameters.forEach { (key, value) -> log.info("$key=$value") }
        return apiGatewayProxyResponseEvent(200, dynamoDbMapper.load(Person::class.java, req.pathParameters["id"]))
    }
}