package framework

import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.InetAddress
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel

class NioServerSocketChannel(private val port: Int, handler: ChannelHandler) : AbstractNioChannel(handler, ServerSocketChannel.open()) {
    private val log = LoggerFactory.getLogger(javaClass)
    override val interestedOps = SelectionKey.OP_ACCEPT
    override val javaChannel = super.javaChannel as ServerSocketChannel
    override fun read(key: SelectionKey): ByteBuffer {
        val buffer = ByteBuffer.allocate(1024)
        val read = (key.channel() as SocketChannel).read(buffer)
        buffer.flip()
        if (read == -1) throw IOException("Socket closed")
        return buffer
    }

    override fun bind() {
        javaChannel.socket().bind(InetSocketAddress(InetAddress.getLocalHost(), port))
        javaChannel.configureBlocking(false)
        log.info("Bound TCP socket at port: {}", port)
    }

    override fun doWrite(pendingWrite: Any, key: SelectionKey) {
        (key.channel() as SocketChannel).write(pendingWrite as ByteBuffer)
    }
}