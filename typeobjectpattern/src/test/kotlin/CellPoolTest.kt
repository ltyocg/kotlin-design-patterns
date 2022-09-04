import kotlin.test.Test
import kotlin.test.assertTrue

class CellPoolTest {
    @Test
    fun `assign random candy types`() {
        val cp = CellPool(10)
        val set = mutableSetOf<String>()
        var parentTypes = 0
        for (i in cp.randomCode.indices) {
            val name = cp.randomCode[i].name
            set.add(name)
            if (name == "fruit" || name == "candy") parentTypes++
        }
        assertTrue(set.size == 5 && parentTypes == 0)
    }
}