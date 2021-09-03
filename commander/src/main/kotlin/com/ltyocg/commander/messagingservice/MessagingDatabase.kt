package com.ltyocg.commander.messagingservice

import com.ltyocg.commander.Database
import java.util.*

class MessagingDatabase : Database<MessagingService.MessageRequest>() {
    private val data = Hashtable<String, MessagingService.MessageRequest>()
    override fun add(obj: MessagingService.MessageRequest): MessagingService.MessageRequest? = data.put(obj.reqId, obj)
    override fun get(id: String): MessagingService.MessageRequest? = data[id]
}