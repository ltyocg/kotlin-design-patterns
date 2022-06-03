abstract class ShardManager {
    protected var shardMap = mutableMapOf<Int, Shard>()
    fun addNewShard(shard: Shard): Boolean {
        val shardId = shard.id
        return if (shardMap.containsKey(shardId)) false
        else {
            shardMap[shardId] = shard
            true
        }
    }

    fun removeShardById(shardId: Int): Boolean = if (shardMap.containsKey(shardId)) {
        shardMap.remove(shardId)
        true
    } else false

    fun getShardById(shardId: Int): Shard? = shardMap[shardId]
    abstract fun storeData(data: Data): Int
    protected abstract fun allocateShard(data: Data): Int
}