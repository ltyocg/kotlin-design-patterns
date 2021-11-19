package com.ltyocg.leaderfollowers

import java.util.concurrent.CopyOnWriteArrayList

class WorkCenter {
    var leader: Worker? = null
        private set
    val workers = CopyOnWriteArrayList<Worker>()
    fun createWorkers(numberOfWorkers: Int, taskSet: TaskSet, taskHandler: TaskHandler) {
        repeat(numberOfWorkers) {
            workers.add(Worker((it - 1).toLong(), this, taskSet, taskHandler))
        }
        promoteLeader()
    }

    fun addWorker(worker: Worker) {
        workers.add(worker)
    }

    fun removeWorker(worker: Worker) {
        workers.remove(worker)
    }

    fun promoteLeader() {
        leader = workers.firstOrNull()
    }
}