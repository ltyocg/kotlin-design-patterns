import org.slf4j.LoggerFactory

class HashShardManager : ShardManager() {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        shardMap[shardId]!!.storeData(data)
        log.info("{} is stored in Shard {}", data, shardId)
        return shardId
    }

    override fun allocateShard(data: Data): Int {
        val shardCount = shardMap.size
        val hash = data.key % shardCount
        return if (hash == 0) shardCount else hash
    }
}