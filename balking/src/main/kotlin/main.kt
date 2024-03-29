import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun main() = runBlocking {
    val washingMachine = WashingMachine()
    repeat(3) {
        launch { washingMachine.wash() }
    }
}
