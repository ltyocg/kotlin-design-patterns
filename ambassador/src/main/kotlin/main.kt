import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    Client().useService(12)
    Client().useService(73)
}