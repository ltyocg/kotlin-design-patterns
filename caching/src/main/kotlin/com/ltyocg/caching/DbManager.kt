package com.ltyocg.caching

import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document

object DbManager {
    private lateinit var dbStore: DbStore
    fun createVirtualDb() {
        dbStore = VirtualDB
        dbStore.init()
    }

    fun connect() {
        dbStore = MongoDB
        dbStore.init()
    }

    fun readFromDb(userId: String): UserAccount? = dbStore.read(userId)
    fun writeToDb(userAccount: UserAccount) {
        dbStore.write(userAccount)
    }

    fun updateDb(userAccount: UserAccount) {
        dbStore.update(userAccount)
    }

    fun upsertDb(userAccount: UserAccount) {
        dbStore.upsert(userAccount)
    }

    private interface DbStore {
        fun init()
        fun read(userId: String): UserAccount?
        fun write(userAccount: UserAccount)
        fun update(userAccount: UserAccount)
        fun upsert(userAccount: UserAccount)
    }

    private object VirtualDB : DbStore {
        private val db = mutableMapOf<String, UserAccount>()
        override fun init() {
            db.clear()
        }

        override fun read(userId: String): UserAccount? = db[userId]
        override fun write(userAccount: UserAccount) {
            db[userAccount.userId] = userAccount
        }

        override fun update(userAccount: UserAccount) {
            write(userAccount)
        }

        override fun upsert(userAccount: UserAccount) {
            write(userAccount)
        }
    }

    private object MongoDB : DbStore {
        private const val USER_ACCOUNT = "user_accounts"
        private const val USER_ID = "userID"
        private const val USER_NAME = "userName"
        private const val ADD_INFO = "additionalInfo"
        private lateinit var db: MongoDatabase

        override fun init() {
            db = MongoClient().getDatabase("test")
        }

        override fun read(userId: String): UserAccount? {
            val iterable = db
                .getCollection(USER_ACCOUNT)
                .find(Document(USER_ID, userId)) ?: return null
            val doc = iterable.first() ?: return null
            return UserAccount(userId, doc.getString(USER_NAME), doc.getString(ADD_INFO))
        }

        override fun write(userAccount: UserAccount) {
            db.getCollection(USER_ACCOUNT)
                .insertOne(
                    buildDocument(
                        USER_ID to userAccount.userId,
                        USER_NAME to userAccount.userName,
                        ADD_INFO to userAccount.additionalInfo
                    )
                )
        }

        override fun update(userAccount: UserAccount) {
            db.getCollection(USER_ACCOUNT).updateOne(
                buildDocument(USER_ID to userAccount.userId),
                buildDocument(
                    "\$set" to buildDocument(
                        USER_NAME to userAccount.userName,
                        ADD_INFO to userAccount.additionalInfo
                    )
                )
            )
        }

        override fun upsert(userAccount: UserAccount) {
            db.getCollection(USER_ACCOUNT).updateOne(
                buildDocument(USER_ID to userAccount.userId),
                buildDocument(
                    "\$set" to buildDocument(
                        USER_ID to userAccount.userId,
                        USER_NAME to userAccount.userName,
                        ADD_INFO to userAccount.additionalInfo
                    )
                ),
                UpdateOptions().upsert(true)
            )
        }

        private fun buildDocument(vararg pairs: Pair<String, Any>): Document = Document().apply { pairs.forEach { append(it.first, it.second) } }
    }
}
