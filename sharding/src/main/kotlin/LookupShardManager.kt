import org.slf4j.LoggerFactory
import java.security.SecureRandom

class LookupShardManager : ShardManager() {
    private val log = LoggerFactory.getLogger(javaClass)
    private val lookupMap = mutableMapOf<Int, Int>()
    override fun storeData(data: Data): Int {
        val shardId = allocateShard(data)
        lookupMap[data.key] = shardId
        shardMap[shardId]!!.storeData(data)
        log.info("{} is stored in Shard {}", data, shardId)
        return shardId
    }

    override fun allocateShard(data: Data): Int {
        val key = data.key
        return if (lookupMap.containsKey(key)) lookupMap[key]!!
        else SecureRandom().nextInt(shardMap.size - 1) + 1
    }
}