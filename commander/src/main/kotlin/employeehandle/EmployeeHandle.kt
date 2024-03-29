package employeehandle

import Order
import Service

class EmployeeHandle(vararg exc: Exception) : Service<Order>(EmployeeDatabase, *exc) {
    override fun receiveRequest(vararg parameters: Any): String? = updateDb(parameters[0] as Order)
    override fun updateDb(vararg parameters: Order): String? {
        val o = parameters[0]
        if (database.get(o.id) == null) {
            database.add(o)
            return o.id
        }
        return null
    }
}