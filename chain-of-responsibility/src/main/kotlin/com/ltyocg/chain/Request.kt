package com.ltyocg.chain

class Request(val requestType: RequestType, private val requestDescription: String) {
    var isHandled = false
        private set

    fun markHandled() {
        isHandled = true
    }

    override fun toString(): String = requestDescription
}