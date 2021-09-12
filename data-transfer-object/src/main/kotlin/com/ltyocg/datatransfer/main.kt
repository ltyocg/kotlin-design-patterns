package com.ltyocg.datatransfer

import com.ltyocg.datatransfer.customer.CustomerDto
import com.ltyocg.datatransfer.customer.CustomerResource
import com.ltyocg.datatransfer.product.Product
import com.ltyocg.datatransfer.product.ProductDto
import com.ltyocg.datatransfer.product.ProductResource
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val customerOne = CustomerDto("1", "Kelly", "Brown")
    val customerResource = CustomerResource(mutableListOf(customerOne, CustomerDto("2", "Alfonso", "Bass")))
    log.info("All customers:-")
    customerResource.printCustomerDetails()
    log.info("----------------------------------------------------------")
    log.info("Deleting customer with id {1}")
    customerResource.delete(customerOne.id)
    customerResource.printCustomerDetails()
    log.info("----------------------------------------------------------")
    log.info("Adding customer three}")
    customerResource.save(CustomerDto("3", "Lynda", "Blair"))
    customerResource.printCustomerDetails()
    val productResource = ProductResource(
        mutableListOf(
            Product(1L, "TV", 1000.0, 1090.0, "Sony"),
            Product(2L, "microwave", 1000.0, 1090.0, "Delonghi"),
            Product(3L, "refrigerator", 1000.0, 1090.0, "Botsch"),
            Product(4L, "airConditioner", 1000.0, 1090.0, "LG"),
        )
    )
    log.info("####### List of products including sensitive data just for admins: \n {}", productResource.allProductsForAdmin)
    log.info("####### List of products for customers: \n {}", productResource.allProductsForCustomer)
    log.info("####### Going to save Sony PS5 ...")
    productResource.save(ProductDto.Request.Create("PS5", 1000.0, 1220.0, "Sony"))
    log.info("####### List of products after adding PS5: \n {}", productResource.products)
}

private fun CustomerResource.printCustomerDetails() {
    allCustomers.forEach { log.info(it.firstName) }
}