import com.ltyocg.commons.FieldAccessor
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class ShardTest {
    private lateinit var data: Data
    private lateinit var shard: Shard

    @BeforeTest
    fun setup() {
        data = Data(1, "test", Data.DataType.TYPE_1)
        shard = Shard(1)
    }

    @Test
    fun storeData() {
        try {
            shard.storeData(data)
            val dataMap: Map<Int, Data> = FieldAccessor(shard)["dataStore"]
            assertEquals(1, dataMap.size)
            assertEquals(data, dataMap[1])
        } catch (e: NoSuchFieldException) {
            fail("Fail to modify field access.")
        } catch (e: IllegalAccessException) {
            fail("Fail to modify field access.")
        }
    }

    @Test
    fun clearData() {
        try {
            var dataMap = mutableMapOf(1 to data)
            FieldAccessor(shard)["dataStore"] = dataMap
            shard.clearData()
            dataMap = FieldAccessor(shard)["dataStore"]
            assertEquals(0, dataMap.size)
        } catch (e: NoSuchFieldException) {
            fail("Fail to modify field access.")
        } catch (e: IllegalAccessException) {
            fail("Fail to modify field access.")
        }
    }
}