package com.ltyocg.leaderfollowers

import org.slf4j.LoggerFactory

data class Worker(
    val id: Long,
    val workCenter: WorkCenter,
    val taskSet: TaskSet,
    val taskHandler: TaskHandler
) : () -> Unit {
    private val log = LoggerFactory.getLogger(this::class.java)
    override fun invoke() {
        while (!Thread.interrupted()) try {
            if (workCenter.leader != null && workCenter.leader != this) {
                var isContinue = false
                synchronized(workCenter) {
                    if (workCenter.leader != null && workCenter.leader != this) {
                        workCenter.wait()
                        isContinue = true
                    }
                }
                if (isContinue) continue
            }
            val task = taskSet.getTask()
            synchronized(workCenter) {
                workCenter.removeWorker(this)
                workCenter.promoteLeader()
                workCenter.notifyAll()
            }
            taskHandler.handleTask(task)
            log.info("The Worker with the ID {} completed the task", id)
            workCenter.addWorker(this)
        } catch (e: InterruptedException) {
            log.warn("Worker interrupted")
            Thread.currentThread().interrupt()
            return
        }
    }

    private fun Any.wait() {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        (this as Object).wait()
    }

    private fun Any.notifyAll() {
        @Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
        (this as Object).notifyAll()
    }
}
