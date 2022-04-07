package app

import framework.*

class App(private val dispatcher: Dispatcher) {
    private lateinit var reactor: NioReactor
    private val channels = mutableListOf<AbstractNioChannel>()
    fun start() {
        reactor = NioReactor(dispatcher)
        val loggingHandler = LoggingHandler()
        reactor
            .registerChannel(tcpChannel(16666, loggingHandler))
            .registerChannel(tcpChannel(16667, loggingHandler))
            .registerChannel(udpChannel(16668, loggingHandler))
            .registerChannel(udpChannel(16669, loggingHandler))
            .start()
    }

    fun stop() {
        reactor.stop()
        dispatcher.stop()
        channels.forEach { it.javaChannel.close() }
    }

    private fun tcpChannel(port: Int, handler: ChannelHandler): AbstractNioChannel = NioServerSocketChannel(port, handler).apply {
        bind()
        channels.add(this)
    }

    private fun udpChannel(port: Int, handler: ChannelHandler): AbstractNioChannel {
        val channel = NioDatagramChannel(port, handler)
        channel.bind()
        channels.add(channel)
        return channel
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = App(ThreadPoolDispatcher(2)).start()
    }
}