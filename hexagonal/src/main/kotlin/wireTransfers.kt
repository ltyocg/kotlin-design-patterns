import com.mongodb.MongoClient
import com.mongodb.client.model.UpdateOptions
import domain.LotteryConstants
import org.bson.Document

interface WireTransfers {
    fun setFunds(bankAccount: String, amount: Int)
    fun getFunds(bankAccount: String): Int
    fun transferFunds(amount: Int, sourceBankAccount: String, destinationBankAccount: String): Boolean
}

class InMemoryBank : WireTransfers {
    private companion object {
        private val accounts = mutableMapOf(LotteryConstants.SERVICE_BANK_ACCOUNT to LotteryConstants.SERVICE_BANK_ACCOUNT_BALANCE)
    }

    override fun setFunds(bankAccount: String, amount: Int) {
        accounts[bankAccount] = amount
    }

    override fun getFunds(bankAccount: String): Int = accounts.getOrDefault(bankAccount, 0)
    override fun transferFunds(amount: Int, sourceBankAccount: String, destinationBankAccount: String): Boolean =
        (accounts.getOrDefault(sourceBankAccount, 0) >= amount).also {
            if (it) {
                accounts.compute(sourceBankAccount) { _, value -> value!! - amount }
                accounts.compute(destinationBankAccount) { _, value -> value!! + amount }
            }
        }
}

class MongoBank(dbName: String, accountsCollectionName: String) : WireTransfers {
    private companion object {
        private const val DEFAULT_DB = "lotteryDB"
        private const val DEFAULT_ACCOUNTS_COLLECTION = "accounts"
    }

    constructor() : this(DEFAULT_DB, DEFAULT_ACCOUNTS_COLLECTION)

    val mongoClient = MongoClient(System.getProperty("mongo-host"), System.getProperty("mongo-port").toInt())
    val database = mongoClient.getDatabase(dbName)
    val accountsCollection = database.getCollection(accountsCollectionName)
    override fun setFunds(bankAccount: String, amount: Int) {
        accountsCollection.updateOne(
            Document("_id", bankAccount),
            Document("\$set", Document("_id", bankAccount).append("funds", amount)),
            UpdateOptions().upsert(true)
        )
    }

    override fun getFunds(bankAccount: String): Int = accountsCollection
        .find(Document("_id", bankAccount))
        .limit(1)
        .into(mutableListOf())
        .firstOrNull()
        ?.getInteger("funds") ?: 0

    override fun transferFunds(amount: Int, sourceBankAccount: String, destinationBankAccount: String): Boolean {
        val sourceFunds = getFunds(sourceBankAccount)
        return (sourceFunds >= amount).also {
            if (it) {
                setFunds(sourceBankAccount, sourceFunds - amount)
                setFunds(destinationBankAccount, getFunds(destinationBankAccount) + amount)
            }
        }
    }
}