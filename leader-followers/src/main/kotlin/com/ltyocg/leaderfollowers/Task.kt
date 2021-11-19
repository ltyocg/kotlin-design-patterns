package com.ltyocg.leaderfollowers

class Task(val time: Int) {
    var finished: Boolean = false
        private set

    fun setFinished() {
        finished = true
    }
}
