class SearchService {
    fun search(type: String?, sortBy: String?): String =
        getQuerySummary(type, sortBy, SortOrder.ASC)

    fun search(type: String?, sortOrder: SortOrder): String =
        getQuerySummary(type, "price", sortOrder)

    fun search(parameterObject: ParameterObject): String = getQuerySummary(
        parameterObject.type,
        parameterObject.sortBy,
        parameterObject.sortOrder
    )

    private fun getQuerySummary(type: String?, sortBy: String?, sortOrder: SortOrder): String =
        "Requesting shoes of type \"$type\" sorted by \"$sortBy\" in \"${sortOrder.value}ending\" order.."
}