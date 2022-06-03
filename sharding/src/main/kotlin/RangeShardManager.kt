import org.slf4j.LoggerFactory

class RangeShardManager : ShardManager() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        shardMap[shardId]!!.storeData(data)
        log.info("{} is stored in Shard {}", data, shardId)
        return shardId
    }

    override fun allocateShard(data: Data): Int = when (data.type) {
        Data.DataType.TYPE_1 -> 1
        Data.DataType.TYPE_2 -> 2
        Data.DataType.TYPE_3 -> 3
    }
}