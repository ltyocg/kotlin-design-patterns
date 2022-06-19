import property.Color
import property.Mass
import property.Movement
import property.Size

interface Creature {
    val name: String
    val size: Size
    val movement: Movement
    val color: Color
    val mass: Mass
}

sealed class AbstractCreature(
    override val name: String,
    override val size: Size,
    override val movement: Movement,
    override val color: Color,
    override val mass: Mass
) : Creature {
    override fun toString(): String = "$name [size=$size, movement=$movement, color=$color, mass=$mass]"
}

class Dragon(mass: Mass = Mass(39300.0)) : AbstractCreature("Dragon", Size.LARGE, Movement.FLYING, Color.RED, mass)
class Goblin(mass: Mass = Mass(30.0)) : AbstractCreature("Goblin", Size.SMALL, Movement.WALKING, Color.GREEN, mass)
class KillerBee(mass: Mass = Mass(6.7)) : AbstractCreature("KillerBee", Size.SMALL, Movement.FLYING, Color.LIGHT, mass)
class Octopus(mass: Mass = Mass(12.0)) : AbstractCreature("Octopus", Size.NORMAL, Movement.SWIMMING, Color.DARK, mass)
class Shark(mass: Mass = Mass(500.0)) : AbstractCreature("Shark", Size.NORMAL, Movement.SWIMMING, Color.LIGHT, mass)
class Troll(mass: Mass = Mass(4000.0)) : AbstractCreature("Troll", Size.LARGE, Movement.WALKING, Color.DARK, mass)