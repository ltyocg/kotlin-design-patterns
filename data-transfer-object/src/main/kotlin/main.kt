import customer.CustomerDto
import customer.CustomerResource
import io.github.oshai.kotlinlogging.KotlinLogging
import product.Product
import product.ProductDto
import product.ProductResource

private val logger = KotlinLogging.logger {}
fun main() {
    val customerOne = CustomerDto("1", "Kelly", "Brown")
    val customerResource = CustomerResource(mutableListOf(customerOne, CustomerDto("2", "Alfonso", "Bass")))
    logger.info { "All customers:-" }
    customerResource.printCustomerDetails()
    logger.info { "----------------------------------------------------------" }
    logger.info { "Deleting customer with id {1}" }
    customerResource.delete(customerOne.id)
    customerResource.printCustomerDetails()
    logger.info { "----------------------------------------------------------" }
    logger.info { "Adding customer three}" }
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
    logger.info { "####### List of products including sensitive data just for admins: \n ${productResource.allProductsForAdmin}" }
    logger.info { "####### List of products for customers: \n ${productResource.allProductsForCustomer}" }
    logger.info { "####### Going to save Sony PS5 ..." }
    productResource.save(ProductDto.Request.Create("PS5", 1000.0, 1220.0, "Sony"))
    logger.info { "####### List of products after adding PS5: \n ${productResource.products}" }
}

private fun CustomerResource.printCustomerDetails() {
    allCustomers.forEach { logger.info { it.firstName } }
}
