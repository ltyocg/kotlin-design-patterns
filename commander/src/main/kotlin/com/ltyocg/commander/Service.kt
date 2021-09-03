package com.ltyocg.commander

abstract class Service<T>
protected constructor(protected val database: Database<T>, vararg exc: Exception) {
    val exceptionsList = mutableListOf(*exc)
    abstract fun receiveRequest(vararg parameters: Any): String?
    protected abstract fun updateDb(vararg parameters: T): String?
}