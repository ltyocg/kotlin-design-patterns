package faas

import kotlinx.serialization.Serializable

@Serializable
class LambdaInfo(
    val awsRequestId: String?,
    val logGroupName: String?,
    val logStreamName: String?,
    val functionName: String?,
    val functionVersion: String?,
    val memoryLimitInMb: Int
)