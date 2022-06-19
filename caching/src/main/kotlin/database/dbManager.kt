package database

import CachingConstants
import UserAccount
import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.UpdateOptions
import org.bson.Document

sealed interface DbManager {
    fun connect()
    fun disconnect()
    fun readFromDb(userId: String): UserAccount?
    fun writeToDb(userAccount: UserAccount): UserAccount
    fun updateDb(userAccount: UserAccount): UserAccount
    fun upsertDb(userAccount: UserAccount): UserAccount
}

internal class MongoDb : DbManager {
    private companion object {
        private const val DATABASE_NAME = "admin"
    }

    private lateinit var client: MongoClient
    private lateinit var db: MongoDatabase
    override fun connect() {
        client = MongoClient(ServerAddress(), MongoCredential.createCredential("root", DATABASE_NAME, "rootpassword".toCharArray()), MongoClientOptions.builder().build())
        db = client.getDatabase(DATABASE_NAME)
    }

    override fun disconnect() = client.close()
    override fun readFromDb(userId: String): UserAccount? {
        val doc = db
            .getCollection(CachingConstants.USER_ACCOUNT)
            .find(Document(CachingConstants.USER_ID, userId))
            .first() ?: return null
        return UserAccount(userId, doc.getString(CachingConstants.USER_NAME), doc.getString(CachingConstants.ADD_INFO))
    }

    override fun writeToDb(userAccount: UserAccount): UserAccount {
        db.getCollection(CachingConstants.USER_ACCOUNT)
            .insertOne(
                buildDocument(
                    CachingConstants.USER_ID to userAccount.userId,
                    CachingConstants.USER_NAME to userAccount.userName,
                    CachingConstants.ADD_INFO to userAccount.additionalInfo
                )
            )
        return userAccount
    }

    override fun updateDb(userAccount: UserAccount): UserAccount {
        db.getCollection(CachingConstants.USER_ACCOUNT).updateOne(
            buildDocument(CachingConstants.USER_ID to userAccount.userId),
            buildDocument(
                "\$set" to buildDocument(
                    CachingConstants.USER_NAME to userAccount.userName,
                    CachingConstants.ADD_INFO to userAccount.additionalInfo
                )
            )
        )
        return userAccount
    }

    override fun upsertDb(userAccount: UserAccount): UserAccount {
        db.getCollection(CachingConstants.USER_ACCOUNT).updateOne(
            buildDocument(CachingConstants.USER_ID to userAccount.userId),
            buildDocument(
                "\$set" to buildDocument(
                    CachingConstants.USER_ID to userAccount.userId,
                    CachingConstants.USER_NAME to userAccount.userName,
                    CachingConstants.ADD_INFO to userAccount.additionalInfo
                )
            ),
            UpdateOptions().upsert(true)
        )
        return userAccount
    }

    private fun buildDocument(vararg pairs: Pair<String, Any>): Document = Document().apply { pairs.forEach { append(it.first, it.second) } }
}

internal class VirtualDb : DbManager {
    private val db = mutableMapOf<String, UserAccount>()
    override fun connect() = db.clear()
    override fun disconnect() = db.clear()
    override fun readFromDb(userId: String): UserAccount? = db[userId]
    override fun writeToDb(userAccount: UserAccount): UserAccount = userAccount.also { db[userAccount.userId] = it }
    override fun updateDb(userAccount: UserAccount): UserAccount = writeToDb(userAccount)
    override fun upsertDb(userAccount: UserAccount): UserAccount = writeToDb(userAccount)
}