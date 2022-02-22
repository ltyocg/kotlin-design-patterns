package customer

class CustomerResource(val allCustomers: MutableList<CustomerDto>) {
    fun save(customerDto: CustomerDto) {
        allCustomers.add(customerDto)
    }

    fun delete(customerId: String) {
        allCustomers.removeIf { it.id == customerId }
    }
}