import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.test.Test
import kotlin.test.assertFalse

class FightTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun `fighter can fight only against same weight opponents`() {
        val fighter = MmaBantamweightFighter("Joe", "Johnson", "The Geek", "Muay Thai")
        val opponents = opponents
        assertFalse(buildList {
            opponents.forEach {
                try {
                    (it as MmaBantamweightFighter).fight(fighter)
                    add(it)
                } catch (e: ClassCastException) {
                    logger.error { e.message }
                }
            }
        }.isEmpty())
    }

    companion object {
        private val opponents
            get() = listOf(
                MmaBantamweightFighter("Ed", "Edwards", "The Problem Solver", "Judo"),
                MmaLightweightFighter("Evan", "Evans", "Clean Coder", "Sambo"),
                MmaHeavyweightFighter("Dave", "Davidson", "The Bug Smasher", "Kickboxing"),
                MmaBantamweightFighter("Ray", "Raymond", "Scrum Master", "Karate"),
                MmaHeavyweightFighter("Jack", "Jackson", "The Pragmatic", "Brazilian Jiu-Jitsu")
            )
    }
}
