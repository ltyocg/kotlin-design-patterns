package framework

import io.github.oshai.kotlinlogging.KotlinLogging
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketAddress
import java.nio.ByteBuffer
import java.nio.channels.DatagramChannel
import java.nio.channels.SelectionKey

class NioDatagramChannel(private val port: Int, handler: ChannelHandler) : AbstractNioChannel(handler, DatagramChannel.open()) {
    private val logger = KotlinLogging.logger {}
    override val interestedOps = SelectionKey.OP_READ
    override fun read(key: SelectionKey): DatagramPacket {
        val buffer = ByteBuffer.allocate(1024).apply { flip() }
        return DatagramPacket(buffer).apply {
            sender = (key.channel() as DatagramChannel).receive(buffer)
        }
    }

    override val javaChannel = super.javaChannel as DatagramChannel
    override fun bind() {
        javaChannel.socket().bind(InetSocketAddress(InetAddress.getLocalHost(), port))
        javaChannel.configureBlocking(false)
        logger.info { "Bound UDP socket at port: $port" }
    }

    override fun doWrite(pendingWrite: Any, key: SelectionKey) {
        val pendingPacket = pendingWrite as DatagramPacket
        javaChannel.send(pendingPacket.data, pendingPacket.receiver)
    }

    class DatagramPacket(val data: ByteBuffer) {
        var sender: SocketAddress? = null
        var receiver: SocketAddress? = null
    }
}
