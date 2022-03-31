data class ParameterObject(
    var type: String? = null,
    var sortBy: String = DEFAULT_SORT_BY,
    var sortOrder: SortOrder = DEFAULT_SORT_ORDER
) {
    companion object {
        const val DEFAULT_SORT_BY = "price"
        val DEFAULT_SORT_ORDER = SortOrder.ASC
    }
}