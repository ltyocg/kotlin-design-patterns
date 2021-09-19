package com.ltyocg.doublechecked.locking

import org.slf4j.LoggerFactory
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class Inventory(private val inventorySize: Int) {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val _items = ArrayList<Item>(inventorySize)
    private val lock = ReentrantLock()

    fun addItem(item: Item): Boolean {
        if (_items.size < inventorySize) lock.withLock {
            if (_items.size < inventorySize) {
                _items.add(item)
                log.info("{}: items.size={}, inventorySize={}", Thread.currentThread(), _items.size, inventorySize)
                return true
            }
        }
        return false
    }

    val items: List<Item>
        get() = _items.toList()
}

class Item