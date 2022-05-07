package baas.api

import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

abstract class AbstractDynamoDbHandler<T> {
    var dynamoDbMapper: DynamoDBMapper = DynamoDBMapper(
        AmazonDynamoDBClientBuilder
            .standard()
            .withRegion(Regions.US_EAST_1)
            .build()
    )

    protected inline fun <reified T> apiGatewayProxyResponseEvent(statusCode: Int, body: T): APIGatewayProxyResponseEvent =
        APIGatewayProxyResponseEvent()
            .withHeaders(mapOf("Content-Type" to "application/json"))
            .withStatusCode(statusCode)
            .withBody(body?.let(Json::encodeToString))
}