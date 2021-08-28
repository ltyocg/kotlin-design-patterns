package com.ltyocg.caching

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    AppManager.initDb(false)
    AppManager.initCacheCapacity(3)
    useReadAndWriteThroughStrategy()
    useReadThroughAndWriteAroundStrategy()
    useReadThroughAndWriteBehindStrategy()
    useCacheAsideStrategy()
}

fun useReadAndWriteThroughStrategy() {
    log.info("# CachingPolicy.THROUGH")
    AppManager.initCachingPolicy(CachingPolicy.THROUGH)
    AppManager.save(UserAccount("001", "John", "He is a boy."))
    log.info(AppManager.printCacheContent())
    AppManager.find("001")
    AppManager.find("001")
}

fun useReadThroughAndWriteAroundStrategy() {
    log.info("# CachingPolicy.AROUND")
    AppManager.initCachingPolicy(CachingPolicy.AROUND)
    AppManager.save(UserAccount("002", "Jane", "She is a girl."))
    log.info(AppManager.printCacheContent())
    AppManager.find("002")
    log.info(AppManager.printCacheContent())
    AppManager.save(AppManager.find("002")!!.copy(userName = "Jane G."))
    log.info(AppManager.printCacheContent())
    AppManager.find("002")
    log.info(AppManager.printCacheContent())
    AppManager.find("002")
}

fun useReadThroughAndWriteBehindStrategy() {
    log.info("# CachingPolicy.BEHIND")
    AppManager.initCachingPolicy(CachingPolicy.BEHIND)
    AppManager.save(UserAccount("003", "Adam", "He likes food."))
    AppManager.save(UserAccount("004", "Rita", "She hates cats."))
    AppManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    log.info(AppManager.printCacheContent())
    AppManager.find("003")
    log.info(AppManager.printCacheContent())
    AppManager.save(UserAccount("006", "Yasha", "She is an only child."))
    log.info(AppManager.printCacheContent())
    AppManager.find("004")
    log.info(AppManager.printCacheContent())
}

fun useCacheAsideStrategy() {
    log.info("# CachingPolicy.ASIDE")
    AppManager.initCachingPolicy(CachingPolicy.ASIDE)
    log.info(AppManager.printCacheContent())
    AppManager.save(UserAccount("003", "Adam", "He likes food."))
    AppManager.save(UserAccount("004", "Rita", "She hates cats."))
    AppManager.save(UserAccount("005", "Isaac", "He is allergic to mustard."))
    log.info(AppManager.printCacheContent())
    AppManager.find("003")
    log.info(AppManager.printCacheContent())
    AppManager.find("004")
    log.info(AppManager.printCacheContent())
}