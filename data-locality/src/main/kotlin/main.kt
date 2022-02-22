import game.GameEntity

private const val NUM_ENTITIES = 5

fun main() {
    with(GameEntity(NUM_ENTITIES)) {
        start()
        update()
    }
}