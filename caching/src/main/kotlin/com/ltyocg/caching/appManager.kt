package com.ltyocg.caching

import com.ltyocg.caching.database.DbManager

class AppManager(private val dbManager: DbManager) {
    private val cacheStore = CacheStore(dbManager)
    private lateinit var cachingPolicy: CachingPolicy
    fun initDb() {
        dbManager.connect()
    }

    fun initCachingPolicy(policy: CachingPolicy) {
        cachingPolicy = policy
        if (cachingPolicy == CachingPolicy.BEHIND) Runtime.getRuntime().addShutdownHook(Thread { cacheStore.flushCache() })
        cacheStore.clearCache()
    }

    fun find(userId: String): UserAccount? = when (cachingPolicy) {
        CachingPolicy.THROUGH, CachingPolicy.AROUND -> cacheStore.readThrough(userId)
        CachingPolicy.BEHIND -> cacheStore.readThroughWithWriteBackPolicy(userId)
        CachingPolicy.ASIDE -> cacheStore[userId] ?: dbManager.readFromDb(userId).also { if (it != null) cacheStore[userId] = it }
    }

    fun save(userAccount: UserAccount) {
        when (cachingPolicy) {
            CachingPolicy.THROUGH -> cacheStore.writeThrough(userAccount)
            CachingPolicy.AROUND -> cacheStore.writeAround(userAccount)
            CachingPolicy.BEHIND -> cacheStore.writeBehind(userAccount)
            CachingPolicy.ASIDE -> {
                dbManager.updateDb(userAccount)
                cacheStore.invalidate(userAccount.userId)
            }
        }
    }

    fun printCacheContent(): String = cacheStore.print()
}

enum class CachingPolicy {
    THROUGH, AROUND, BEHIND, ASIDE
}