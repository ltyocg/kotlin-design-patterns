package framework

import io.github.oshai.kotlinlogging.KotlinLogging
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey

interface ChannelHandler {
    fun handleChannelRead(channel: AbstractNioChannel, readObject: Any, key: SelectionKey)
}

class LoggingHandler : ChannelHandler {
    override fun handleChannelRead(channel: AbstractNioChannel, readObject: Any, key: SelectionKey) = when (readObject) {
        is ByteBuffer -> {
            doLogging(readObject)
            sendReply(channel, key)
        }

        is NioDatagramChannel.DatagramPacket -> {
            doLogging(readObject.data)
            sendReply(channel, readObject, key)
        }

        else -> error("Unknown data received")
    }

    private companion object {
        private val logger = KotlinLogging.logger {}
        private val ack = "Data logged successfully".toByteArray()
        private fun sendReply(channel: AbstractNioChannel, incomingPacket: NioDatagramChannel.DatagramPacket, key: SelectionKey) =
            channel.write(NioDatagramChannel.DatagramPacket(ByteBuffer.wrap(ack)).apply {
                receiver = incomingPacket.sender
            }, key)

        private fun sendReply(channel: AbstractNioChannel, key: SelectionKey) = channel.write(ByteBuffer.wrap(ack), key)
        private fun doLogging(data: ByteBuffer) = logger.info { String(data.array(), 0, data.limit()) }
    }
}
