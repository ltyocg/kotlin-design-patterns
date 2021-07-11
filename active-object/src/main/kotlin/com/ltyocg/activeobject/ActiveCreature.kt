package com.ltyocg.activeobject

import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.RejectedExecutionException

abstract class ActiveCreature internal constructor(val name: String) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val executorService = Executors.newSingleThreadExecutor()
    var status = 0
        private set

    fun eat() {
        submit {
            log.info("{} is eating!", name)
            log.info("{} has finished eating!", name)
        }
    }

    fun roam() {
        submit {
            log.info("{} has started to roam in the wastelands.", name)
        }
    }

    fun kill(status: Int = 0) {
        this.status = status
        executorService.shutdownNow()
    }

    private fun submit(task: Runnable) {
        try {
            executorService.submit {
                try {
                    task.run()
                } catch (e: InterruptedException) {
                    if (status != 0) log.error("Thread was interrupted. --> {}", e.localizedMessage)
                    executorService.shutdown()
                }
            }
        } catch (e: RejectedExecutionException) {
        }
    }
}