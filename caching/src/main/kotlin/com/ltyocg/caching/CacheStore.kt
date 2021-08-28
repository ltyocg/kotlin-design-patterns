package com.ltyocg.caching

import org.slf4j.LoggerFactory

object CacheStore {
    private val log = LoggerFactory.getLogger(this::class.java)
    private val cache = LruCache(0)

    fun initCapacity(capacity: Int) {
        cache.capacity = capacity
    }

    fun readThrough(userId: String): UserAccount? {
        if (userId in cache) {
            log.info("# Cache Hit!")
            return cache[userId]
        }
        log.info("# Cache Miss!")
        return DbManager.readFromDb(userId)?.also { cache[userId] = it }
    }

    fun writeThrough(userAccount: UserAccount) {
        if (userAccount.userId in cache) DbManager.updateDb(userAccount)
        else DbManager.writeToDb(userAccount)
        cache[userAccount.userId] = userAccount
    }

    fun writeAround(userAccount: UserAccount) {
        if (userAccount.userId in cache) {
            DbManager.updateDb(userAccount)
            cache.invalidate(userAccount.userId)
        } else DbManager.writeToDb(userAccount)
    }

    fun readThroughWithWriteBackPolicy(userId: String): UserAccount? {
        if (userId in cache) {
            log.info("# Cache Hit!")
            return cache[userId]
        }
        log.info("# Cache Miss!")
        val userAccount = DbManager.readFromDb(userId)
        if (cache.full) {
            log.info("# Cache is FULL! Writing LRU data to DB...")
            DbManager.upsertDb(cache.lruData)
        }
        return userAccount.also { if (it != null) cache[userId] = it }
    }

    fun writeBehind(userAccount: UserAccount) {
        if (cache.full && userAccount.userId !in cache) {
            log.info("# Cache is FULL! Writing LRU data to DB...")
            DbManager.upsertDb(cache.lruData)
        }
        cache[userAccount.userId] = userAccount
    }

    fun clearCache() {
        cache.clear()
    }

    fun flushCache() {
        log.info("# flushCache...")
        cache.cacheDataInListForm.forEach(DbManager::updateDb)
    }

    fun print(): String = cache.cacheDataInListForm.joinToString("", "\n--CACHE CONTENT--\n", "----\n") { "$it\n" }
    operator fun get(userId: String): UserAccount? = cache[userId]
    operator fun set(userId: String, userAccount: UserAccount) {
        cache[userId] = userAccount
    }

    fun invalidate(userId: String) {
        cache.invalidate(userId)
    }
}