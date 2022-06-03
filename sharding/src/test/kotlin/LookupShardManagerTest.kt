import com.ltyocg.commons.FieldAccessor
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class LookupShardManagerTest {
    private lateinit var lookupShardManager: LookupShardManager

    @BeforeTest
    fun setup() {
        lookupShardManager = LookupShardManager().apply {
            addNewShard(Shard(1))
            addNewShard(Shard(2))
            addNewShard(Shard(3))
        }
    }

    @Test
    fun storeData() {
        try {
            val data = Data(1, "test", Data.DataType.TYPE_1)
            lookupShardManager.storeData(data)
            val lookupMap: Map<Int, Int> = FieldAccessor(lookupShardManager)["lookupMap"]
            assertEquals(data, lookupShardManager.getShardById(lookupMap[1]!!)!!.getDataById(1))
        } catch (e: NoSuchFieldException) {
            fail("Fail to modify field access.")
        } catch (e: IllegalAccessException) {
            fail("Fail to modify field access.")
        }
    }
}