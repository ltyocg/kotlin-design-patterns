package com.ltyocg.dao

import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

class DbCustomerDao(private val dataSource: DataSource) : CustomerDao {
    override val all: Sequence<Customer>
        get() = object : Iterator<Customer> {
            private val connection = dataSource.connection
            private val statement = connection.prepareStatement("SELECT * FROM CUSTOMERS")
            private val resultSet = statement.executeQuery()
            private var hasNextTemporary = false
            override fun hasNext(): Boolean =
                if (hasNextTemporary) true
                else resultSet.next().also {
                    if (it) hasNextTemporary = true
                    else {
                        resultSet.close()
                        statement.close()
                        connection.close()
                    }
                }

            override fun next(): Customer =
                if (hasNextTemporary || hasNext()) {
                    hasNextTemporary = false
                    createCustomer(resultSet)
                } else throw NoSuchElementException()
        }.asSequence()

    override fun getById(id: Int): Customer? = statement("SELECT * FROM CUSTOMERS WHERE ID = ?") {
        setInt(1, id)
        executeQuery().use { resultSet -> if (resultSet.next()) createCustomer(resultSet) else null }
    }

    override fun add(customer: Customer): Boolean =
        if (getById(customer.id) != null) false
        else statement("INSERT INTO CUSTOMERS VALUES (?,?,?)") {
            setInt(1, customer.id)
            setString(2, customer.firstName)
            setString(3, customer.lastName)
            execute()
            true
        }

    override fun update(customer: Customer): Boolean = statement("UPDATE CUSTOMERS SET FNAME = ?, LNAME = ? WHERE ID = ?") {
        setString(1, customer.firstName)
        setString(2, customer.lastName)
        setInt(3, customer.id)
        executeUpdate() > 0
    }

    override fun delete(customer: Customer): Boolean = statement("DELETE FROM CUSTOMERS WHERE ID = ?") {
        setInt(1, customer.id)
        executeUpdate() > 0
    }

    private fun createCustomer(resultSet: ResultSet): Customer = Customer(resultSet.getInt("ID"), resultSet.getString("FNAME"), resultSet.getString("LNAME"))
    private fun <R> statement(sql: String, block: PreparedStatement.() -> R): R = dataSource.connection.use { it.prepareStatement(sql).use(block) }
}