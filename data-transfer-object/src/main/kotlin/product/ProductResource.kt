package product

class ProductResource(val products: MutableList<Product>) {
    val allProductsForAdmin: List<ProductDto.Response.Private>
        get() = products.map { ProductDto.Response.Private(it.id, it.name, it.price, it.cost) }

    val allProductsForCustomer: List<ProductDto.Response.Public>
        get() = products.map { ProductDto.Response.Public(it.id, it.name, it.price) }

    fun save(createProductDto: ProductDto.Request.Create) {
        products.add(
            Product(
                products.size + 1L,
                createProductDto.name,
                createProductDto.price,
                createProductDto.cost,
                createProductDto.supplier
            )
        )
    }
}