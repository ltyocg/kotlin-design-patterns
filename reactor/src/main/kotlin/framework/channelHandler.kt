package framework

import org.slf4j.LoggerFactory
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
        else -> throw IllegalStateException("Unknown data received")
    }

    companion object {
        private val log = LoggerFactory.getLogger(LoggingHandler::class.java)
        private val ack = "Data logged successfully".toByteArray()
        private fun sendReply(channel: AbstractNioChannel, incomingPacket: NioDatagramChannel.DatagramPacket, key: SelectionKey) =
            channel.write(NioDatagramChannel.DatagramPacket(ByteBuffer.wrap(ack)).apply {
                receiver = incomingPacket.sender
            }, key)

        private fun sendReply(channel: AbstractNioChannel, key: SelectionKey) = channel.write(ByteBuffer.wrap(ack), key)
        private fun doLogging(data: ByteBuffer) = log.info(String(data.array(), 0, data.limit()))
    }
}