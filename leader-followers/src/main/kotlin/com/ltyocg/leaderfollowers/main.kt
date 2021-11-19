package com.ltyocg.leaderfollowers

import java.security.SecureRandom
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.streams.asSequence

fun main() {
    val taskSet = TaskSet()
    val workCenter = WorkCenter()
    workCenter.createWorkers(4, taskSet, TaskHandler())
    val workers = workCenter.workers
    val exec = Executors.newFixedThreadPool(workers.size)
    workers.forEach { exec.submit { it() } }
    Thread.sleep(1000)
    SecureRandom().ints(0, 1000).asSequence()
        .take(5)
        .map(::Task)
        .forEach(taskSet::addTask)
    exec.awaitTermination(2, TimeUnit.SECONDS)
    exec.shutdownNow()
}