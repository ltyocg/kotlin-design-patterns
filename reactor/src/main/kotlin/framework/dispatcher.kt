package framework

import java.nio.channels.SelectionKey
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

interface Dispatcher {
    fun onChannelReadEvent(channel: AbstractNioChannel, readObject: Any, key: SelectionKey)
    fun stop()
}

class SameThreadDispatcher : Dispatcher {
    override fun onChannelReadEvent(channel: AbstractNioChannel, readObject: Any, key: SelectionKey) =
        channel.handler.handleChannelRead(channel, readObject, key)

    override fun stop() {}
}

class ThreadPoolDispatcher(poolSize: Int) : Dispatcher {
    private val executorService = Executors.newFixedThreadPool(poolSize)
    override fun onChannelReadEvent(channel: AbstractNioChannel, readObject: Any, key: SelectionKey) =
        executorService.execute { channel.handler.handleChannelRead(channel, readObject, key) }

    override fun stop() {
        executorService.shutdown()
        if (executorService.awaitTermination(4, TimeUnit.SECONDS)) executorService.shutdownNow()
    }
}