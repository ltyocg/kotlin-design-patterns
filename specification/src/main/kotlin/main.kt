import io.github.oshai.kotlinlogging.KotlinLogging
import property.Color
import property.Movement

private val logger = KotlinLogging.logger {}
fun main() {
    val creatures = listOf(Goblin(), Octopus(), Dragon(), Shark(), Troll(), KillerBee())
    logger.info { "Demonstrating hard-coded specification :" }
    logger.info { "Find all walking creatures" }
    creatures.print(MovementSelector(Movement.WALKING))
    logger.info { "Find all dark creatures" }
    creatures.print(ColorSelector(Color.DARK))
    logger.info { "\n" }
    logger.info { "Demonstrating parameterized specification :" }
    logger.info { "Find all creatures heavier than 600kg" }
    creatures.print(MassGreaterThanSelector(600.0))
    logger.info { "Find all creatures lighter than or weighing exactly 500kg" }
    creatures.print(MassSmallerThanOrEqSelector(500.0))
    logger.info { "\n" }
    logger.info { "Demonstrating composite specification :" }
    logger.info { "Find all red and flying creatures" }
    creatures.print(ColorSelector(Color.RED) and MovementSelector(Movement.FLYING))
    logger.info { "Find all scary creatures" }
    creatures.print(
        ColorSelector(Color.DARK)
                or ColorSelector(Color.RED)
                and !MovementSelector(Movement.SWIMMING)
                and (MassGreaterThanSelector(400.0) or MassEqualSelector(400.0))
    )
}

private fun List<Creature>.print(selector: (Creature) -> Boolean) = asSequence()
    .filter(selector)
    .forEach { logger.info { it } }
