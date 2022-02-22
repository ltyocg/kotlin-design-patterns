import database.DbManagerFactory
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
private const val USE_MONGO_DB = "--mongo"
fun main(args: Array<String>) {
    val appManager = AppManager(DbManagerFactory.initDb(USE_MONGO_DB in args)).apply { initDb() }
    useReadAndWriteThroughStrategy(appManager)
    useReadThroughAndWriteAroundStrategy(appManager)
    useReadThroughAndWriteBehindStrategy(appManager)
    useCacheAsideStrategy(appManager)
}

fun useReadAndWriteThroughStrategy(appManager: AppManager) {
    log.info("# CachingPolicy.THROUGH")
    appManager.initCachingPolicy(CachingPolicy.THROUGH)
    appManager.save(UserAccount("001", "John", "He is a boy."))
    log.info(appManager.printCacheContent())
    appManager.find("001")
    appManager.find("001")
}

fun useReadThroughAndWriteAroundStrategy(appManager: AppManager) {
    log.info("# CachingPolicy.AROUND")
    appManager.initCachingPolicy(CachingPolicy.AROUND)
    appManager.save(UserAccount("002", "Jane", "She is a girl."))
    log.info(appManager.printCacheContent())
    appManager.find("002")
    log.info(appManager.printCacheContent())
    appManager.save(appManager.find("002")!!.copy(userName = "Jane G."))
    log.info(appManager.printCacheContent())
    appManager.find("002")
    log.info(appManager.printCacheContent())
    appManager.find("002")
}

fun useReadThroughAndWriteBehindStrategy(appManager: AppManager) {
    log.info("# CachingPolicy.BEHIND")
    appManager.initCachingPolicy(CachingPolicy.BEHIND)
    appManager.save(UserAccount("003", "Adam", "He likes food."))
    appManager.save(UserAccount("004", "Rita", "She hates cats."))
    appManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    log.info(appManager.printCacheContent())
    appManager.find("003")
    log.info(appManager.printCacheContent())
    appManager.save(UserAccount("006", "Yasha", "She is an only child."))
    log.info(appManager.printCacheContent())
    appManager.find("004")
    log.info(appManager.printCacheContent())
}

fun useCacheAsideStrategy(appManager: AppManager) {
    log.info("# CachingPolicy.ASIDE")
    appManager.initCachingPolicy(CachingPolicy.ASIDE)
    log.info(appManager.printCacheContent())
    appManager.save(UserAccount("003", "Adam", "He likes food."))
    appManager.save(UserAccount("004", "Rita", "She hates cats."))
    appManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    log.info(appManager.printCacheContent())
    appManager.find("003")
    log.info(appManager.printCacheContent())
    appManager.find("004")
    log.info(appManager.printCacheContent())
}