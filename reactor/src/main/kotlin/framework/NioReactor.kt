package framework

import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NioReactor(private val dispatcher: Dispatcher) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val selector = Selector.open()
    private val pendingCommands = ConcurrentLinkedQueue<() -> Unit>()
    private val reactorMain = Executors.newSingleThreadExecutor()
    fun start() = reactorMain.execute {
        try {
            log.info("Reactor started, waiting for events...")
            eventLoop()
        } catch (e: IOException) {
            log.error("exception in event loop", e)
        }
    }

    fun stop() {
        reactorMain.shutdown()
        selector.wakeup()
        if (!reactorMain.awaitTermination(4, TimeUnit.SECONDS)) reactorMain.shutdownNow()
        selector.close()
        log.info("Reactor stopped")
    }

    fun registerChannel(channel: AbstractNioChannel): NioReactor {
        channel.javaChannel.register(selector, channel.interestedOps).attach(channel)
        channel.setReactor(this)
        return this
    }

    private fun eventLoop() {
        while (!Thread.interrupted()) {
            processPendingCommands()
            selector.select()
            val keys = selector.selectedKeys()
            val iterator = keys.iterator()
            while (iterator.hasNext()) {
                val key = iterator.next()
                if (key.isValid) processKey(key)
                else iterator.remove()
            }
            keys.clear()
        }
    }

    private fun processPendingCommands() {
        val iterator = pendingCommands.iterator()
        while (iterator.hasNext()) {
            iterator.next()()
            iterator.remove()
        }
    }

    private fun processKey(key: SelectionKey) {
        when {
            key.isAcceptable -> onChannelAcceptable(key)
            key.isReadable -> onChannelReadable(key)
            key.isWritable -> onChannelWritable(key)
        }
    }

    private fun onChannelReadable(key: SelectionKey) = try {
        dispatchReadEvent(key, (key.attachment() as AbstractNioChannel).read(key))
    } catch (e: IOException) {
        try {
            key.channel().close()
        } catch (e1: IOException) {
            log.error("error closing channel", e1)
        }
    }

    private fun dispatchReadEvent(key: SelectionKey, readObject: Any) =
        dispatcher.onChannelReadEvent(key.attachment() as AbstractNioChannel, readObject, key)

    private fun onChannelAcceptable(key: SelectionKey) {
        val socketChannel = (key.channel() as ServerSocketChannel).accept()
        socketChannel.configureBlocking(false)
        socketChannel.register(selector, SelectionKey.OP_READ).attach(key.attachment())
    }

    private fun onChannelWritable(key: SelectionKey) = (key.attachment() as AbstractNioChannel).flush(key)
    fun changeOps(key: SelectionKey, interestedOps: Int) {
        pendingCommands.add(ChangeKeyOpsCommand(key, interestedOps))
        selector.wakeup()
    }

    internal class ChangeKeyOpsCommand(
        private val key: SelectionKey,
        private val interestedOps: Int
    ) : () -> Unit {
        override fun invoke() {
            key.interestOps(interestedOps)
        }

        override fun toString(): String = "Change of ops to: $interestedOps"
    }
}