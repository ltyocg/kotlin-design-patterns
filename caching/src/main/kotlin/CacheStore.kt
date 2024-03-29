import database.DbManager
import io.github.oshai.kotlinlogging.KotlinLogging

class CacheStore(private val dbManager: DbManager) {
    private val logger = KotlinLogging.logger {}
    private val cache = LruCache(3)
    fun readThrough(userId: String): UserAccount? {
        if (userId in cache) {
            logger.info { "# Cache Hit!" }
            return cache[userId]
        }
        logger.info { "# Cache Miss!" }
        return dbManager.readFromDb(userId)?.also { cache[userId] = it }
    }

    fun writeThrough(userAccount: UserAccount) {
        if (userAccount.userId in cache) dbManager.updateDb(userAccount)
        else dbManager.writeToDb(userAccount)
        cache[userAccount.userId] = userAccount
    }

    fun writeAround(userAccount: UserAccount) {
        if (userAccount.userId in cache) {
            dbManager.updateDb(userAccount)
            cache.invalidate(userAccount.userId)
        } else dbManager.writeToDb(userAccount)
    }

    fun readThroughWithWriteBackPolicy(userId: String): UserAccount? {
        if (userId in cache) {
            logger.info { "# Cache Hit!" }
            return cache[userId]
        }
        logger.info { "# Cache Miss!" }
        val userAccount = dbManager.readFromDb(userId)
        if (cache.full) {
            logger.info { "# Cache is FULL! Writing LRU data to DB..." }
            dbManager.upsertDb(cache.lruData)
        }
        return userAccount.also { if (it != null) cache[userId] = it }
    }

    fun writeBehind(userAccount: UserAccount) {
        if (cache.full && userAccount.userId !in cache) {
            logger.info { "# Cache is FULL! Writing LRU data to DB..." }
            dbManager.upsertDb(cache.lruData)
        }
        cache[userAccount.userId] = userAccount
    }

    fun clearCache() = cache.clear()
    fun flushCache() {
        logger.info { "# flushCache..." }
        cache.cacheDataInListForm.forEach(dbManager::updateDb)
    }

    fun print(): String = cache.cacheDataInListForm.joinToString("", "\n--CACHE CONTENT--\n", "----\n") { "$it\n" }
    operator fun get(userId: String): UserAccount? = cache[userId]
    operator fun set(userId: String, userAccount: UserAccount) {
        cache[userId] = userAccount
    }

    fun invalidate(userId: String) = cache.invalidate(userId)
}
