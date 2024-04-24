package app

import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.io.PrintWriter
import java.net.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object AppClient {
    private val logger = KotlinLogging.logger {}
    private val service = Executors.newFixedThreadPool(4)

    @JvmStatic
    fun main(args: Array<String>) = start()
    fun start() {
        logger.info { "Starting logging clients" }
        with(service) {
            execute(TcpLoggingClient("Client 1", 16666))
            execute(TcpLoggingClient("Client 2", 16667))
            execute(UdpLoggingClient("Client 3", 16668))
            execute(UdpLoggingClient("Client 4", 16669))
        }
    }

    fun stop() {
        service.shutdown()
        if (!service.isTerminated) {
            service.shutdownNow()
            try {
                service.awaitTermination(1000, TimeUnit.SECONDS)
            } catch (e: InterruptedException) {
                logger.error(e) { "exception awaiting termination" }
            }
        }
        logger.info { "Logging clients stopped" }
    }

    fun artificialDelayOf(millis: Long) = try {
        Thread.sleep(millis)
    } catch (e: InterruptedException) {
        logger.error(e) { "sleep interrupted" }
    }

    private class TcpLoggingClient(private val clientName: String, private val serverPort: Int) : () -> Unit {
        override fun invoke() {
            try {
                Socket(InetAddress.getLocalHost(), serverPort).use { socket ->
                    val writer = PrintWriter(socket.getOutputStream())
                    repeat(4) {
                        writer.println("$clientName - Log request: $it")
                        writer.flush()
                        val data = ByteArray(1024)
                        val read = socket.getInputStream().read(data, 0, data.size)
                        logger.info { if (read == 0) "Read zero bytes" else String(data, 0, read) }
                        artificialDelayOf(100)
                    }
                }
            } catch (e: IOException) {
                logger.error(e) { "error sending requests" }
                throw RuntimeException(e)
            }
        }

    }

    private class UdpLoggingClient(private val clientName: String, port: Int) : () -> Unit {
        private val remoteAddress = InetSocketAddress(InetAddress.getLocalHost(), port)
        override fun invoke() = try {
            DatagramSocket().use { socket ->
                repeat(4) {
                    val bytes = "$clientName - Log request: $it".toByteArray()
                    socket.send(DatagramPacket(bytes, bytes.size, remoteAddress))
                    val data = ByteArray(1024)
                    val reply = DatagramPacket(data, data.size)
                    socket.receive(reply)
                    logger.info { if (reply.length == 0) "Read zero bytes" else String(reply.data, 0, reply.length) }
                    artificialDelayOf(100)
                }
            }
        } catch (e: IOException) {
            logger.error(e) { "error sending packets" }
        }
    }
}
