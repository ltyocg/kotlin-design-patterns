package queue

import Database
import java.util.*

class QueueDatabase(vararg exc: Exception) : Database<QueueTask>() {
    private val data: Queue<QueueTask> = LinkedList()
    val exceptionsList = mutableListOf(*exc)
    override fun add(obj: QueueTask): QueueTask {
        data.offer(obj)
        return obj
    }

    fun peek(): QueueTask = data.element()
    fun dequeue(): QueueTask = data.remove()
    override fun get(id: String): QueueTask? = null
}