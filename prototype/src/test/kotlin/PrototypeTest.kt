import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class PrototypeTest<P : Prototype> {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun testPrototype(testedPrototype: P, expectedToString: String) {
        assertEquals(expectedToString, testedPrototype.toString())
        val clone = testedPrototype.copy()
        assertNotSame(clone, testedPrototype)
        assertSame(testedPrototype::class, clone::class)
        assertEquals(clone, testedPrototype)
    }

    companion object {
        @JvmStatic
        fun dataProvider(): Collection<Array<Any>> = listOf(
            arrayOf(OrcBeast("axe"), "Orcish wolf attacks with axe"),
            arrayOf(OrcMage("sword"), "Orcish mage attacks with sword"),
            arrayOf(OrcWarlord("laser"), "Orcish warlord attacks with laser"),
            arrayOf(ElfBeast("cooking"), "Elven eagle helps in cooking"),
            arrayOf(ElfMage("cleaning"), "Elven mage helps in cleaning"),
            arrayOf(ElfWarlord("protecting"), "Elven warlord helps in protecting")
        )
    }
}