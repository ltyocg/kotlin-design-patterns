package com.ltyocg.commander

abstract class Database<T> {
    abstract fun add(obj: T): T?
    abstract fun get(id: String): T?
}