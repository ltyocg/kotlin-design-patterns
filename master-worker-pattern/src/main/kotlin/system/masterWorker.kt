package system

import Input
import Result

abstract class MasterWorker(numOfWorkers: Int, masterInitializer: (Int) -> Master) {
    private val master: Master = masterInitializer(numOfWorkers)
    fun getResult(input: Input<*>): Result<*>? {
        master.doWork(input)
        return master.finalResult
    }
}

class ArrayTransposeMasterWorker : MasterWorker(4, ::ArrayTransposeMaster)