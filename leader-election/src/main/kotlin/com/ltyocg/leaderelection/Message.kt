package com.ltyocg.leaderelection

data class Message(
    var type: MessageType? = null,
    var content: String = ""
)
