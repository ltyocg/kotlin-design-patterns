import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import property.Color
import property.Mass
import property.Movement
import property.Size
import kotlin.test.assertEquals

class CreatureTest {
    @ParameterizedTest
    @MethodSource("dataProvider")
    fun name(testedCreature: Creature, name: String) = assertEquals(name, testedCreature.name)

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun size(testedCreature: Creature, name: String, size: Size) = assertEquals(size, testedCreature.size)

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun movement(testedCreature: Creature, name: String, size: Size, movement: Movement) = assertEquals(movement, testedCreature.movement)

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun color(testedCreature: Creature, name: String, size: Size, movement: Movement, color: Color) = assertEquals(color, testedCreature.color)

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun mass(testedCreature: Creature, name: String, size: Size, movement: Movement, color: Color, mass: Mass) = assertEquals(mass, testedCreature.mass)

    @ParameterizedTest
    @MethodSource("dataProvider")
    fun `test toString`(testedCreature: Creature, name: String, size: Size, movement: Movement, color: Color, mass: Mass) =
        assertEquals("$name [size=$size, movement=$movement, color=$color, mass=$mass]", testedCreature.toString())

    private companion object {
        @JvmStatic
        private fun dataProvider(): Collection<Array<Any>> = listOf(
            arrayOf(Dragon(), "Dragon", Size.LARGE, Movement.FLYING, Color.RED, Mass(39300.0)),
            arrayOf(Goblin(), "Goblin", Size.SMALL, Movement.WALKING, Color.GREEN, Mass(30.0)),
            arrayOf(KillerBee(), "KillerBee", Size.SMALL, Movement.FLYING, Color.LIGHT, Mass(6.7)),
            arrayOf(Octopus(), "Octopus", Size.NORMAL, Movement.SWIMMING, Color.DARK, Mass(12.0)),
            arrayOf(Shark(), "Shark", Size.NORMAL, Movement.SWIMMING, Color.LIGHT, Mass(500.0)),
            arrayOf(Troll(), "Troll", Size.LARGE, Movement.WALKING, Color.DARK, Mass(4000.0))
        )
    }
}