package com.ltyocg.domainmodel

import org.joda.money.Money
import org.slf4j.LoggerFactory
import java.sql.SQLException

class Customer(
    private val customerDao: CustomerDao,
    val name: String,
    var money: Money
) {
    private val log = LoggerFactory.getLogger(javaClass)
    var purchases = mutableListOf<Product>()

    fun save() {
        if (customerDao.findByName(name) != null) customerDao.update(this)
        else customerDao.save(this)
    }

    fun buyProduct(product: Product) {
        log.info("%s want to buy %s($%.2f)...".format(name, product.name, product.salePrice.amount))
        if (money < product.salePrice) {
            log.error("Not enough money!")
            return
        }
        try {
            money = money.minus(product.salePrice)
            customerDao.addProduct(product, this)
            purchases.add(product)
            log.info("{} bought {}!", name, product.name)
        } catch (e: SQLException) {
            receiveMoney(product.salePrice)
            log.error(e.localizedMessage)
        }
    }

    fun returnProduct(product: Product) {
        log.info("%s want to return %s($%.2f)...".format(name, product.name, product.salePrice.amount))
        if (product in purchases) try {
            customerDao.deleteProduct(product, this)
            purchases.remove(product)
            receiveMoney(product.salePrice)
            log.info("{} returned {}!", name, product.name)
        } catch (e: SQLException) {
            log.error(e.localizedMessage)
        } else log.error("{} didn't buy {}...", name, product.name)
    }

    fun showPurchases() {
        if (purchases.isEmpty()) log.info("{} didn't bought anything", name)
        else log.info("{} bought: {}", name, purchases.joinToString { "${it.name} - \$${it.salePrice.amount}" })
    }

    fun showBalance() {
        log.info("$name balance $money")
    }

    private fun receiveMoney(amount: Money) {
        money = money.plus(amount)
    }
}