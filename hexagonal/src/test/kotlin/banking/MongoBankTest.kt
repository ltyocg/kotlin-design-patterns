package banking

import MongoBank
import com.mongodb.client.MongoClients
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.transitions.Mongod
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MongoBankTest {
    @BeforeTest
    fun init() {
        System.setProperty("mongo-host", "localhost")
        System.setProperty("mongo-port", "27017")
    }

    @Test
    fun test() = Mongod.instance().start(Version.Main.V7_0).use { running ->
        MongoClients.create("mongodb://${running.current().serverAddress}").use { mongo ->
            val mongoDatabase = mongo.getDatabase("lotteryDBTest")
            val mongoBank = MongoBank(mongoDatabase.name, "testAccounts")
            assertEquals(0, mongoBank.accountsCollection.countDocuments())
            assertEquals(0, mongoBank.getFunds("000-000"))
            mongoBank.setFunds("000-000", 10)
            assertEquals(10, mongoBank.getFunds("000-000"))
            assertEquals(0, mongoBank.getFunds("111-111"))
            mongoBank.transferFunds(9, "000-000", "111-111")
            assertEquals(1, mongoBank.getFunds("000-000"))
            assertEquals(9, mongoBank.getFunds("111-111"))
        }
    }
}
