package com.ltyocg.dao

interface CustomerDao {
    val all: Sequence<Customer>
    fun getById(id: Int): Customer?
    fun add(customer: Customer): Boolean
    fun update(customer: Customer): Boolean
    fun delete(customer: Customer): Boolean
}