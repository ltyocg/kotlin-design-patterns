import io.github.oshai.kotlinlogging.KotlinLogging

class HashShardManager : ShardManager() {
    private val logger = KotlinLogging.logger {}
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        shardMap[shardId]!!.storeData(data)
        logger.info { "$data is stored in Shard $shardId" }
        return shardId
    }

    override fun allocateShard(data: Data): Int {
        val shardCount = shardMap.size
        val hash = data.key % shardCount
        return if (hash == 0) shardCount else hash
    }
}
