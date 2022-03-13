package system

import ArrayInput
import ArrayResult
import Input
import Result

abstract class Worker(
    private val master: Master,
    val workerId: Int
) : Thread() {
    lateinit var receivedData: Input<*>
    protected abstract fun executeOperation(): Result<*>
    override fun run() = master.receiveData(executeOperation(), this)
}

class ArrayTransposeWorker(master: Master, workerId: Int) : Worker(master, workerId) {
    public override fun executeOperation(): ArrayResult {
        val arrayInput = receivedData as ArrayInput
        val rows = arrayInput.data[0].size
        val cols = arrayInput.data.size
        val resultData = Array(rows) { IntArray(cols) { 0 } }
        repeat(cols) { i -> repeat(rows) { j -> resultData[j][i] = arrayInput.data[i][j] } }
        return ArrayResult(resultData)
    }
}