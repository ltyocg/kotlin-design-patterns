package faas.api

import com.amazonaws.services.lambda.runtime.Context
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test

class LambdaInfoApiHandlerTest {
    @Test
    fun `handleRequest with mock context`() {
        val context = mock<Context>()
        whenever(context.awsRequestId).thenReturn("mock aws request id")
        @Suppress("CAST_NEVER_SUCCEEDS")
        LambdaInfoApiHandler().handleRequest(null, mock())
    }
}