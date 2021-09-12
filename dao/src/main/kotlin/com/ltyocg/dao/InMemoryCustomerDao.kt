package com.ltyocg.dao

class InMemoryCustomerDao : CustomerDao {
    private val idToCustomer = mutableMapOf<Int, Customer>()
    override val all: Sequence<Customer>
        get() = idToCustomer.values.asSequence()

    override fun getById(id: Int): Customer? = idToCustomer[id]

    override fun add(customer: Customer): Boolean = if (idToCustomer[customer.id] != null) false
    else {
        idToCustomer[customer.id] = customer
        true
    }

    override fun update(customer: Customer): Boolean = idToCustomer.replace(customer.id, customer) != null
    override fun delete(customer: Customer): Boolean = idToCustomer.remove(customer.id) != null
}