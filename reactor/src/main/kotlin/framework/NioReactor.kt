package framework

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class NioReactor(private val dispatcher: Dispatcher) {
    private val logger = KotlinLogging.logger {}
    private val selector = Selector.open()
    private val pendingCommands = ConcurrentLinkedQueue<() -> Unit>()
    private val reactorMain = Executors.newSingleThreadExecutor()
    fun start() = reactorMain.execute {
        try {
            logger.info { "Reactor started, waiting for events..." }
            eventLoop()
        } catch (e: IOException) {
            logger.error(e) { "exception in event loop" }
        }
    }

    fun stop() {
        reactorMain.shutdown()
        selector.wakeup()
        if (!reactorMain.awaitTermination(4, TimeUnit.SECONDS)) reactorMain.shutdownNow()
        selector.close()
        logger.info { "Reactor stopped" }
    }

    fun registerChannel(channel: AbstractNioChannel): NioReactor = apply {
        channel.javaChannel.register(selector, channel.interestedOps).attach(channel)
        channel.setReactor(this)
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
            logger.error(e1) { "error closing channel" }
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
        class ChangeKeyOpsCommand(private val key: SelectionKey, private val interestedOps: Int) : () -> Unit {
            override fun invoke() {
                key.interestOps(interestedOps)
            }

            override fun toString(): String = "Change of ops to: $interestedOps"
        }
        pendingCommands.add(ChangeKeyOpsCommand(key, interestedOps))
        selector.wakeup()
    }
}
