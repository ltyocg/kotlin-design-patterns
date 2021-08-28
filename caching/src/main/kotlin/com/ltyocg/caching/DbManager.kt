package com.ltyocg.caching

import com.ltyocg.caching.constants.CachingConstants
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document

object DbManager {
    private lateinit var db: MongoDatabase
    private var useMongoDB = false
    private val virtualDB = mutableMapOf<String, UserAccount>()

    fun createVirtualDb() {
        useMongoDB = false
        virtualDB.clear()
    }

    fun connect() {
        useMongoDB = true
        db = MongoClient().getDatabase("test")
    }

    fun readFromDb(userId: String): UserAccount? {
        if (!useMongoDB) return virtualDB[userId]
        val iterable = db
            .getCollection(CachingConstants.USER_ACCOUNT)
            .find(Document(CachingConstants.USER_ID, userId)) ?: return null
        val doc = iterable.first() ?: return null
        val userName = doc.getString(CachingConstants.USER_NAME)
        val appInfo = doc.getString(CachingConstants.ADD_INFO)
        return UserAccount(userId, userName, appInfo)
    }

    fun writeToDb(userAccount: UserAccount) = writeElvisVirtual(userAccount) {
        db.getCollection(CachingConstants.USER_ACCOUNT)
            .insertOne(
                buildDocument(
                    CachingConstants.USER_ID to it.userId,
                    CachingConstants.USER_NAME to it.userName,
                    CachingConstants.ADD_INFO to it.additionalInfo
                )
            )
    }

    fun updateDb(userAccount: UserAccount) = writeElvisVirtual(userAccount) {
        db.getCollection(CachingConstants.USER_ACCOUNT).updateOne(
            buildDocument(CachingConstants.USER_ID to it.userId),
            buildDocument(
                "\$set" to buildDocument(
                    CachingConstants.USER_NAME to it.userName,
                    CachingConstants.ADD_INFO to it.additionalInfo
                )
            )
        )
    }

    fun upsertDb(userAccount: UserAccount) = writeElvisVirtual(userAccount) {
        db.getCollection(CachingConstants.USER_ACCOUNT).updateOne(
            buildDocument(CachingConstants.USER_ID to it.userId),
            buildDocument(
                "\$set" to buildDocument(
                    CachingConstants.USER_ID to it.userId,
                    CachingConstants.USER_NAME to it.userName,
                    CachingConstants.ADD_INFO to it.additionalInfo
                )
            ),
            UpdateOptions().upsert(true)
        )
    }

    private inline fun writeElvisVirtual(userAccount: UserAccount, block: (UserAccount) -> Unit) {
        if (useMongoDB) block(userAccount)
        else virtualDB[userAccount.userId] = userAccount
    }

    private fun buildDocument(vararg pairs: Pair<String, Any>): Document = Document().apply { pairs.forEach { append(it.first, it.second) } }
}
