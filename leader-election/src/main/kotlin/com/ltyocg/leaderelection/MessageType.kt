package com.ltyocg.leaderelection

enum class MessageType {
    ELECTION,
    LEADER,
    HEARTBEAT,
    ELECTION_INVOKE,
    LEADER_INVOKE,
    HEARTBEAT_INVOKE
}