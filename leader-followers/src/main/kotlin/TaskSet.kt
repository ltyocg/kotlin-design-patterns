import java.util.concurrent.ArrayBlockingQueue

class TaskSet {
    private val queue = ArrayBlockingQueue<Task>(100)
    fun addTask(task: Task) {
        queue.add(task)
    }

    fun getTask(): Task = queue.take()
    val size: Int
        get() = queue.size
}