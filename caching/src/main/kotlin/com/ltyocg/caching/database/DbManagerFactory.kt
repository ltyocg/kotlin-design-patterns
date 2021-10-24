package com.ltyocg.caching.database

object DbManagerFactory {
    fun initDb(isMongo: Boolean): DbManager = if (isMongo) MongoDb() else VirtualDb()
}