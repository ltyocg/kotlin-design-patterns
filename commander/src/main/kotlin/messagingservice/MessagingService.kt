package messagingservice

import Service
import generateId
import io.github.oshai.kotlinlogging.KotlinLogging

class MessagingService(vararg exc: Exception) : Service<MessagingService.MessageRequest>(MessagingDatabase, *exc) {
    private val logger = KotlinLogging.logger {}
    override fun receiveRequest(vararg parameters: Any): String? = updateDb(
        MessageRequest(
            generateId(), when (parameters[0] as Int) {
                0 -> MessageToSend.PAYMENT_FAIL
                1 -> MessageToSend.PAYMENT_TRYING
                else -> MessageToSend.PAYMENT_SUCCESSFUL
            }
        )
    )

    override fun updateDb(vararg parameters: MessageRequest): String? {
        val req = parameters[0]
        if (database.get(req.reqId) == null) {
            database.add(req)
            logger.info { req.msg.message }
            return req.reqId
        }
        return null
    }

    enum class MessageToSend(message: String) {
        PAYMENT_FAIL(
            """
                Msg: There was an error in your payment process.
                Your order is placed and has been converted to COD.
                Please reach us on CUSTOMER-CARE-NUBER in case of any queries.
                Thank you for shopping with us!
            """
        ),
        PAYMENT_TRYING(
            """
                Msg: There was an error in your payment process,
                we are working on it and will return back to you shortly.
                Meanwhile, your order has been placed and will be shipped.
            """
        ),
        PAYMENT_SUCCESSFUL(
            """
                Msg: Your order has been placed and paid for successfully!
                Thank you for shopping with us!
            """
        );

        val message = message.trimIndent().replace('\n', ' ')
    }

    data class MessageRequest(val reqId: String, val msg: MessageToSend)
}
