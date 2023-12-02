import database.DbManagerFactory
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) {
    val appManager = AppManager(DbManagerFactory.initDb("--mongo" in args)).apply { initDb() }
    useReadAndWriteThroughStrategy(appManager)
    useReadThroughAndWriteAroundStrategy(appManager)
    useReadThroughAndWriteBehindStrategy(appManager)
    useCacheAsideStrategy(appManager)
}

fun useReadAndWriteThroughStrategy(appManager: AppManager) {
    logger.info { "# CachingPolicy.THROUGH" }
    appManager.initCachingPolicy(CachingPolicy.THROUGH)
    appManager.save(UserAccount("001", "John", "He is a boy."))
    logger.info { appManager.printCacheContent() }
    appManager.find("001")
    appManager.find("001")
}

fun useReadThroughAndWriteAroundStrategy(appManager: AppManager) {
    logger.info { "# CachingPolicy.AROUND" }
    appManager.initCachingPolicy(CachingPolicy.AROUND)
    appManager.save(UserAccount("002", "Jane", "She is a girl."))
    logger.info { appManager.printCacheContent() }
    appManager.find("002")
    logger.info { appManager.printCacheContent() }
    appManager.save(appManager.find("002")!!.copy(userName = "Jane G."))
    logger.info { appManager.printCacheContent() }
    appManager.find("002")
    logger.info { appManager.printCacheContent() }
    appManager.find("002")
}

fun useReadThroughAndWriteBehindStrategy(appManager: AppManager) {
    logger.info { "# CachingPolicy.BEHIND" }
    appManager.initCachingPolicy(CachingPolicy.BEHIND)
    appManager.save(UserAccount("003", "Adam", "He likes food."))
    appManager.save(UserAccount("004", "Rita", "She hates cats."))
    appManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    logger.info { appManager.printCacheContent() }
    appManager.find("003")
    logger.info { appManager.printCacheContent() }
    appManager.save(UserAccount("006", "Yasha", "She is an only child."))
    logger.info { appManager.printCacheContent() }
    appManager.find("004")
    logger.info { appManager.printCacheContent() }
}

fun useCacheAsideStrategy(appManager: AppManager) {
    logger.info { "# CachingPolicy.ASIDE" }
    appManager.initCachingPolicy(CachingPolicy.ASIDE)
    logger.info { appManager.printCacheContent() }
    appManager.save(UserAccount("003", "Adam", "He likes food."))
    appManager.save(UserAccount("004", "Rita", "She hates cats."))
    appManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    logger.info { appManager.printCacheContent() }
    appManager.find("003")
    logger.info { appManager.printCacheContent() }
    appManager.find("004")
    logger.info { appManager.printCacheContent() }
}
