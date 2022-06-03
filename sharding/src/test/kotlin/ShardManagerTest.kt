import com.ltyocg.commons.FieldAccessor
import kotlin.test.*

class ShardManagerTest {
    private lateinit var shardManager: ShardManager

    @BeforeTest
    fun setup() {
        shardManager = TestShardManager()
    }

    @Test
    fun addNewShard() {
        try {
            val shard = Shard(1)
            shardManager.addNewShard(shard)
            val map: Map<Int, Shard> = FieldAccessor(shardManager)["shardMap"]
            assertEquals(1, map.size)
            assertEquals(shard, map[1])
        } catch (e: NoSuchFieldException) {
            fail("Fail to modify field access.")
        } catch (e: IllegalAccessException) {
            fail("Fail to modify field access.")
        }
    }

    @Test
    fun removeShardById() {
        try {
            shardManager.addNewShard(Shard(1))
            assertTrue(shardManager.removeShardById(1))
            val map: Map<Int, Shard> = FieldAccessor(shardManager)["shardMap"]
            assertEquals(0, map.size)
        } catch (e: IllegalAccessException) {
            fail("Fail to modify field access.")
        } catch (e: NoSuchFieldException) {
            fail("Fail to modify field access.")
        }
    }

    @Test
    fun getShardById() {
        val shard = Shard(1)
        shardManager.addNewShard(shard)
        assertEquals(shard, shardManager.getShardById(1))
    }

    private class TestShardManager : ShardManager() {
        override fun storeData(data: Data): Int = 0
        override fun allocateShard(data: Data): Int = 0
    }
}