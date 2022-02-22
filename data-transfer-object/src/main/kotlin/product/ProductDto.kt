package product

object ProductDto {
    object Request {
        class Create(
            override val name: String?,
            override val price: Double?,
            override val cost: Double?,
            override val supplier: String?
        ) : Name, Price, Cost, Supplier
    }

    object Response {
        data class Public(
            override var id: Long?,
            override val name: String?,
            override val price: Double?
        ) : Id, Name, Price

        data class Private(
            override var id: Long?,
            override val name: String?,
            override val price: Double?,
            override val cost: Double?
        ) : Id, Name, Price, Cost
    }

    interface Id {
        var id: Long?
    }

    interface Name {
        val name: String?
    }

    interface Price {
        val price: Double?
    }

    interface Cost {
        val cost: Double?
    }

    interface Supplier {
        val supplier: String?
    }
}