package faas

class ApiGatewayResponse(
    val statusCode: Int?,
    val body: String?,
    val headers: Map<String, String>?,
    val isBase64Encoded: Boolean? = false
)