package framework

import java.nio.channels.SelectableChannel
import java.nio.channels.SelectionKey
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

abstract class AbstractNioChannel(val handler: ChannelHandler, channel: SelectableChannel) {
    private val channelToPendingWrites = ConcurrentHashMap<SelectableChannel, Queue<Any>>()
    private lateinit var reactor: NioReactor
    open val javaChannel = channel
    abstract val interestedOps: Int
    abstract fun bind()
    abstract fun read(key: SelectionKey): Any
    fun setReactor(reactor: NioReactor) {
        this.reactor = reactor
    }

    fun flush(key: SelectionKey) {
        val pendingWrites = channelToPendingWrites[key.channel()]!!
        lateinit var pendingWrite: Any
        while (pendingWrites.poll()?.also { pendingWrite = it } != null) doWrite(pendingWrite, key)
        reactor.changeOps(key, SelectionKey.OP_READ)
    }

    protected abstract fun doWrite(pendingWrite: Any, key: SelectionKey)
    open fun write(data: Any, key: SelectionKey) {
        (channelToPendingWrites[key.channel()]
            ?: synchronized(channelToPendingWrites) { channelToPendingWrites[key.channel()] }
            ?: ConcurrentLinkedQueue<Any>().also { channelToPendingWrites[key.channel()] = it })
            .add(data)
        reactor.changeOps(key, SelectionKey.OP_WRITE)
    }
}