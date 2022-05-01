import kotlinx.coroutines.delay

suspend fun main() {
    val creatures = (0 until 3)
        .map { Orc(Orc::class.simpleName + it) }
        .onEach {
            it.eat()
            it.roam()
        }
        .toList()
    delay(1000)
    creatures.forEach { it.kill() }
}