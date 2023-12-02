import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.properties.Delegates

class LruCache(capacity: Int) {
    private val logger = KotlinLogging.logger {}
    var capacity: Int by Delegates.observable(capacity) { _, _, newValue ->
        if (cache.size > newValue) with(cache.iterator()) {
            repeat(cache.size - newValue) {
                next()
                remove()
            }
        }
    }
    private val cache = object : LinkedHashMap<String, UserAccount>(16, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, UserAccount>): Boolean =
            (size > this@LruCache.capacity).also { if (it) logger.info { "# Cache is FULL! Removing ${eldest.key} from cache..." } }
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
        if (cache.remove(userId) != null) logger.info { "# $userId has been updated! Removing older version from cache..." }
    }

    fun clear() = cache.clear()
}
