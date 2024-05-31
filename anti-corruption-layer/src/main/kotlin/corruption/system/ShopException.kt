package corruption.system

class ShopException(lhs: String, rhs: String) : Exception(
    """
    The order is already placed but has an incorrect data:
    Incoming order:  $lhs
    Existing order:  $rhs
    """.trimIndent()
)
