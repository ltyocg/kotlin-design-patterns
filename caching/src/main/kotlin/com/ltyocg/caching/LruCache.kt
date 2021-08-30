package com.ltyocg.caching

import org.slf4j.LoggerFactory

class LruCache(var capacity: Int) {
    private val log = LoggerFactory.getLogger(this::class.java)

    private val cache = object : LinkedHashMap<String, UserAccount>(16, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, UserAccount>): Boolean {
            return (size > capacity).also { if (it) log.info("# Cache is FULL! Removing {} from cache...", eldest.key) }
        }
    }
    val full: Boolean
        get() = cache.size >= capacity
    val lruData: UserAccount
        get() = cache.values.first()
    val cacheDataInListForm: List<UserAccount>
        get() = cache.values.reversed()

    operator fun get(userId: String): UserAccount? = cache[userId]
    operator fun set(userId: String, userAccount: UserAccount) {
        cache[userId] = userAccount
    }

    operator fun contains(userId: String): Boolean = userId in cache
    fun invalidate(userId: String) {
        if (cache.remove(userId) != null) log.info("# {} has been updated! Removing older version from cache...", userId)
    }

    fun clear() {
        cache.clear()
    }
}
