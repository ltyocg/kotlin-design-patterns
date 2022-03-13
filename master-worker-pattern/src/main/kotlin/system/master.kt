package system

import ArrayResult
import Input
import Result
import java.util.*

abstract class Master(private val numOfWorkers: Int) {
    protected var workers: List<Worker> = setWorkers(numOfWorkers)
    val allResultData = Hashtable<Int, Result<*>>(numOfWorkers)
    var expectedNumResults = 0
    var finalResult: Result<*>? = null
    protected abstract fun setWorkers(num: Int): List<Worker>
    fun doWork(input: Input<*>) {
        val dividedInput = input.divideData(numOfWorkers)
        expectedNumResults = dividedInput.size
        repeat(expectedNumResults) {
            workers[it].apply {
                receivedData = dividedInput[it]
                start()
            }
        }
        repeat(expectedNumResults) {
            try {
                workers[it].join()
            } catch (e: InterruptedException) {
                System.err.println("Error while executing thread")
            }
        }
    }

    fun receiveData(data: Result<*>, w: Worker) {
        allResultData[w.workerId] = data
        if (allResultData.size == expectedNumResults) finalResult = aggregateData()
    }

    protected abstract fun aggregateData(): Result<*>
}

class ArrayTransposeMaster(numOfWorkers: Int) : Master(numOfWorkers) {
    override fun setWorkers(num: Int): List<Worker> =
        (0 until num).map { ArrayTransposeWorker(this, it + 1) }

    override fun aggregateData(): Result<*> {
        val rows = (allResultData.values.first() as ArrayResult).data.size
        val columns = allResultData.values.sumOf { (it.data as Array<IntArray>)[0].size }
        val resultData = Array(rows) { IntArray(columns) { 0 } }
        var columnsDone = 0
        repeat(expectedNumResults) {
            val work = (allResultData[workers[it].workerId] as ArrayResult).data
            repeat(work.size) { m -> System.arraycopy(work[m], 0, resultData[m], columnsDone, work[0].size) }
            columnsDone += work[0].size
        }
        return ArrayResult(resultData)
    }
}