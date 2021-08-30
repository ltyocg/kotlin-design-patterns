package com.ltyocg.caching

object AppManager {
    private lateinit var cachingPolicy: CachingPolicy

    fun initDb(useMongoDb: Boolean) {
        if (useMongoDb) DbManager.connect()
        else DbManager.createVirtualDb()
    }

    fun initCachingPolicy(policy: CachingPolicy) {
        cachingPolicy = policy
        if (cachingPolicy == CachingPolicy.BEHIND) Runtime.getRuntime().addShutdownHook(Thread { CacheStore.flushCache() })
        CacheStore.clearCache()
    }

    fun initCacheCapacity(capacity: Int) {
        CacheStore.initCapacity(capacity)
    }

    fun find(userId: String): UserAccount? = when (cachingPolicy) {
        CachingPolicy.THROUGH, CachingPolicy.AROUND -> CacheStore.readThrough(userId)
        CachingPolicy.BEHIND -> CacheStore.readThroughWithWriteBackPolicy(userId)
        CachingPolicy.ASIDE -> CacheStore[userId] ?: DbManager.readFromDb(userId).also { if (it != null) CacheStore[userId] = it }
    }

    fun save(userAccount: UserAccount) {
        when (cachingPolicy) {
            CachingPolicy.THROUGH -> CacheStore.writeThrough(userAccount)
            CachingPolicy.AROUND -> CacheStore.writeAround(userAccount)
            CachingPolicy.BEHIND -> CacheStore.writeBehind(userAccount)
            CachingPolicy.ASIDE -> {
                DbManager.updateDb(userAccount)
                CacheStore.invalidate(userAccount.userId)
            }
        }
    }

    fun printCacheContent(): String = CacheStore.print()
}

enum class CachingPolicy {
    THROUGH, AROUND, BEHIND, ASIDE
}