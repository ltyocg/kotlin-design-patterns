fun main() {
    val data1 = Data(1, "data1", Data.DataType.TYPE_1)
    val data2 = Data(2, "data2", Data.DataType.TYPE_2)
    val data3 = Data(3, "data3", Data.DataType.TYPE_3)
    val data4 = Data(4, "data4", Data.DataType.TYPE_1)
    val shard1 = Shard(1)
    val shard2 = Shard(2)
    val shard3 = Shard(3)
    fun manager(manager: ShardManager) {
        manager.addNewShard(shard1)
        manager.addNewShard(shard2)
        manager.addNewShard(shard3)
        manager.storeData(data1)
        manager.storeData(data2)
        manager.storeData(data3)
        manager.storeData(data4)
        shard1.clearData()
        shard2.clearData()
        shard3.clearData()
    }

    manager(LookupShardManager())
    manager(RangeShardManager())
    manager(HashShardManager())
}