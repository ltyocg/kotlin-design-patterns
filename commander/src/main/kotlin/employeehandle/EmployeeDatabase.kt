package employeehandle

import Database
import Order

class EmployeeDatabase : Database<Order>() {
    private val data = mutableMapOf<String, Order>()
    override fun add(obj: Order): Order? = data.put(obj.id, obj)
    override fun get(id: String): Order? = data[id]
}