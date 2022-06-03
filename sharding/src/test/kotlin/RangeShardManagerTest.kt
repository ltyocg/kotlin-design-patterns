import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class RangeShardManagerTest {
    private lateinit var rangeShardManager: RangeShardManager

    @BeforeTest
    fun setup() {
        rangeShardManager = RangeShardManager().apply {
            addNewShard(Shard(1))
            addNewShard(Shard(2))
            addNewShard(Shard(3))
        }
    }

    @Test
    fun storeData() {
        val data = Data(1, "test", Data.DataType.TYPE_1)
        rangeShardManager.storeData(data)
        assertEquals(data, rangeShardManager.getShardById(1)!!.getDataById(1))
    }
}