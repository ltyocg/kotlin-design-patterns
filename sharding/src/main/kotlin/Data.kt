data class Data(var key: Int, var value: String, var type: DataType) {
    enum class DataType {
        TYPE_1, TYPE_2, TYPE_3
    }
}