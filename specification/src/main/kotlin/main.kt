import org.slf4j.LoggerFactory
import property.Color
import property.Movement

private val log = LoggerFactory.getLogger("main")
fun main() {
    val creatures = listOf(Goblin(), Octopus(), Dragon(), Shark(), Troll(), KillerBee())
    log.info("Demonstrating hard-coded specification :")
    log.info("Find all walking creatures")
    creatures.print(MovementSelector(Movement.WALKING))
    log.info("Find all dark creatures")
    creatures.print(ColorSelector(Color.DARK))
    log.info("\n")
    log.info("Demonstrating parameterized specification :")
    log.info("Find all creatures heavier than 600kg")
    creatures.print(MassGreaterThanSelector(600.0))
    log.info("Find all creatures lighter than or weighing exactly 500kg")
    creatures.print(MassSmallerThanOrEqSelector(500.0))
    log.info("\n")
    log.info("Demonstrating composite specification :")
    log.info("Find all red and flying creatures")
    creatures.print(ColorSelector(Color.RED) and MovementSelector(Movement.FLYING))
    log.info("Find all scary creatures")
    creatures.print(
        ColorSelector(Color.DARK)
                or ColorSelector(Color.RED)
                and !MovementSelector(Movement.SWIMMING)
                and (MassGreaterThanSelector(400.0) or MassEqualSelector(400.0))
    )
}

private fun List<Creature>.print(selector: (Creature) -> Boolean) = asSequence()
    .filter { selector(it) }
    .map { it.toString() }
    .forEach(log::info)