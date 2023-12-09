import io.github.oshai.kotlinlogging.KotlinLogging
import java.security.SecureRandom

class LookupShardManager : ShardManager() {
    private val logger = KotlinLogging.logger {}
    private val lookupMap = mutableMapOf<Int, Int>()
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        lookupMap[data.key] = shardId
        shardMap[shardId]!!.storeData(data)
        logger.info { "$data is stored in Shard $shardId" }
        return shardId
    }

    override fun allocateShard(data: Data): Int {
        val key = data.key
        return if (lookupMap.containsKey(key)) lookupMap[key]!!
        else SecureRandom().nextInt(shardMap.size - 1) + 1
    }
}
