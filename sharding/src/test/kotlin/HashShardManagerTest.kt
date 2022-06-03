import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HashShardManagerTest {
    private lateinit var hashShardManager: HashShardManager

    @BeforeTest
    fun setup() {
        hashShardManager = HashShardManager().apply {
            addNewShard(Shard(1))
            addNewShard(Shard(2))
            addNewShard(Shard(3))
        }
    }

    @Test
    fun storeData() {
        val data = Data(1, "test", Data.DataType.TYPE_1)
        hashShardManager.storeData(data)
        assertEquals(data, hashShardManager.getShardById(1)!!.getDataById(1))
    }
}