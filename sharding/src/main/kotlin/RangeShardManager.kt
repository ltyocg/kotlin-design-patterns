import io.github.oshai.kotlinlogging.KotlinLogging

class RangeShardManager : ShardManager() {
    private val logger = KotlinLogging.logger {}
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        shardMap[shardId]!!.storeData(data)
        logger.info { "$data is stored in Shard $shardId" }
        return shardId
    }

    override fun allocateShard(data: Data): Int = when (data.type) {
        Data.DataType.TYPE_1 -> 1
        Data.DataType.TYPE_2 -> 2
        Data.DataType.TYPE_3 -> 3
    }
}
