class Shard(val id: Int) {
    private val dataStore = mutableMapOf<Int, Data>()
    fun storeData(data: Data) {
        dataStore[data.key] = data
    }

    fun clearData() = dataStore.clear()
    fun getDataById(id: Int): Data? = dataStore[id]
}