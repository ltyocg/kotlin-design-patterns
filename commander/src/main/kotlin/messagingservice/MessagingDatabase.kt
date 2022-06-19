package messagingservice

import Database
import java.util.*

object MessagingDatabase : Database<MessagingService.MessageRequest>() {
    private val data = Hashtable<String, MessagingService.MessageRequest>()
    override fun add(obj: MessagingService.MessageRequest): MessagingService.MessageRequest? = data.put(obj.reqId, obj)
    override fun get(id: String): MessagingService.MessageRequest? = data[id]
}