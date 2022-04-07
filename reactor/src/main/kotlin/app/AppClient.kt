package app

import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.PrintWriter
import java.net.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object AppClient {
    private val log = LoggerFactory.getLogger(javaClass)
    private val service = Executors.newFixedThreadPool(4)

    @JvmStatic
    fun main(args: Array<String>) = start()
    fun start() {
        log.info("Starting logging clients")
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
                log.error("exception awaiting termination", e)
            }
        }
        log.info("Logging clients stopped")
    }

    fun artificialDelayOf(millis: Long) = try {
        Thread.sleep(millis)
    } catch (e: InterruptedException) {
        log.error("sleep interrupted", e)
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
                        if (read == 0) log.info("Read zero bytes")
                        else log.info(String(data, 0, read))
                        artificialDelayOf(100)
                    }
                }
            } catch (e: IOException) {
                log.error("error sending requests", e)
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
                    if (reply.length == 0) log.info("Read zero bytes")
                    else log.info(String(reply.data, 0, reply.length))
                    artificialDelayOf(100)
                }
            }
        } catch (e: IOException) {
            log.error("error sending packets", e)
        }
    }
}