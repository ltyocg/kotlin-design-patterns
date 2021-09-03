package com.ltyocg.commander.employeehandle

import com.ltyocg.commander.Database
import com.ltyocg.commander.Order

class EmployeeDatabase : Database<Order>() {
    private val data = mutableMapOf<String, Order>()
    override fun add(obj: Order): Order? = data.put(obj.id, obj)
    override fun get(id: String): Order? = data[id]
}