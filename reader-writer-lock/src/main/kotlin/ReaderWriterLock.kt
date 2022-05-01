import org.slf4j.LoggerFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReadWriteLock

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
class ReaderWriterLock : ReadWriteLock {
    private val log = LoggerFactory.getLogger(javaClass)
    private val readerMutex = Any()
    private var currentReaderCount = 0
    private val globalMutex = mutableSetOf<Any>()
    private val readerLock = ReadLock()
    private val writerLock = WriteLock()
    override fun readLock(): Lock = readerLock
    override fun writeLock(): Lock = writerLock
    private fun doesWriterOwnThisLock(): Boolean = writerLock in globalMutex
    private val isLockFree: Boolean
        get() = globalMutex.isEmpty()

    private inner class ReadLock : Lock {
        override fun lock() = synchronized(readerMutex) {
            if (++currentReaderCount == 1) acquireForReaders()
        }

        private fun acquireForReaders() {
            synchronized(globalMutex) {
                while (doesWriterOwnThisLock()) try {
                    (globalMutex as Object).wait()
                } catch (e: InterruptedException) {
                    log.info("InterruptedException while waiting for globalMutex in acquireForReaders", e)
                    Thread.currentThread().interrupt()
                }
                globalMutex.add(this)
            }
        }

        override fun unlock() = synchronized(readerMutex) {
            if (--currentReaderCount == 0) {
                synchronized(globalMutex) {
                    globalMutex.remove(this)
                    (globalMutex as Object).notifyAll()
                }
            }
        }

        override fun lockInterruptibly() = throw UnsupportedOperationException()
        override fun tryLock(): Boolean = throw UnsupportedOperationException()
        override fun tryLock(time: Long, unit: TimeUnit): Boolean = throw UnsupportedOperationException()
        override fun newCondition(): Condition = throw UnsupportedOperationException()
    }

    private inner class WriteLock : Lock {
        override fun lock() {
            synchronized(globalMutex) {
                while (!isLockFree) try {
                    (globalMutex as Object).wait()
                } catch (e: InterruptedException) {
                    log.info("InterruptedException while waiting for globalMutex to begin writing", e)
                    Thread.currentThread().interrupt()
                }
                globalMutex.add(this)
            }
        }

        override fun unlock() = synchronized(globalMutex) {
            globalMutex.remove(this)
            (globalMutex as Object).notifyAll()
        }

        override fun lockInterruptibly() = throw UnsupportedOperationException()
        override fun tryLock(): Boolean = throw UnsupportedOperationException()
        override fun tryLock(time: Long, unit: TimeUnit): Boolean = throw UnsupportedOperationException()
        override fun newCondition(): Condition = throw UnsupportedOperationException()
    }
}