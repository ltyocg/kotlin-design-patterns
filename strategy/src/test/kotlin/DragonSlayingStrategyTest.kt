import com.ltyocg.commons.assertListAppender
import com.ltyocg.commons.clear
import com.ltyocg.commons.lastMessage
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class DragonSlayingStrategyTest {
    private val assertListAppender = assertListAppender(
        LambdaStrategy::class,
        MeleeStrategy::class,
        ProjectileStrategy::class,
        SpellStrategy::class
    )

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun execute(strategy: DragonSlayingStrategy, expectedResult: String) {
        assertListAppender.clear()
        strategy.execute()
        assertEquals(expectedResult, assertListAppender.lastMessage())
        assertEquals(1, assertListAppender.list.size)
    }

    private companion object {
        @JvmStatic
        fun dataProvider(): Collection<Array<Any>> = listOf(
            arrayOf(MeleeStrategy, "With your Excalibur you sever the dragon's head!"),
            arrayOf(ProjectileStrategy, "You shoot the dragon with the magical crossbow and it falls dead on the ground!"),
            arrayOf(SpellStrategy, "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
        )
    }
}