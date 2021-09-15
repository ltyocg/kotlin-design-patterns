package com.ltyocg.domainmodel

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.sql.Date
import java.sql.PreparedStatement
import javax.sql.DataSource

interface ProductDao {
    fun findByName(name: String): Product?
    fun save(product: Product)
    fun update(product: Product)
}

class ProductDaoImpl(private val dataSource: DataSource) : ProductDao {
    override fun findByName(name: String): Product? = statement("select * from PRODUCTS where name = ?;") {
        setString(1, name)
        val rs = executeQuery()
        if (rs.next()) Product(
            this@ProductDaoImpl,
            rs.getString("name"),
            Money.of(CurrencyUnit.USD, rs.getBigDecimal("price")),
            rs.getDate("expiration_date").toLocalDate()
        )
        else null
    }

    override fun save(product: Product) = statement<Unit>("insert into PRODUCTS (name, price, expiration_date) values (?, ?, ?)") {
        setString(1, product.name)
        setBigDecimal(2, product.price.amount)
        setDate(3, Date.valueOf(product.expirationDate))
        executeUpdate()
    }

    override fun update(product: Product) = statement<Unit>("update PRODUCTS set price = ?, expiration_date = ? where name = ?;") {
        setBigDecimal(1, product.price.amount)
        setDate(2, Date.valueOf(product.expirationDate))
        setString(3, product.name)
        executeUpdate()
    }

    private fun <R> statement(sql: String, block: PreparedStatement.() -> R): R = dataSource.connection.use { it.prepareStatement(sql).use(block) }
}