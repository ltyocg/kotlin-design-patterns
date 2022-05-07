package faas.api

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import faas.ApiGatewayResponse
import faas.LambdaInfo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

class LambdaInfoApiHandler : RequestHandler<Map<String, Any?>?, ApiGatewayResponse> {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun handleRequest(input: Map<String, Any?>?, context: Context): ApiGatewayResponse {
        log.info("received: $input")
        return ApiGatewayResponse(
            200,
            Json.encodeToString(
                LambdaInfo(
                    context.awsRequestId,
                    context.logGroupName,
                    context.logStreamName,
                    context.functionName,
                    context.functionVersion,
                    context.memoryLimitInMB
                )
            ),
            mapOf("Content-Type" to "application/json")
        )
    }
}