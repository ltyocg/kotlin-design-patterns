import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

suspend fun main() = withContext(Dispatchers.Default) {
    val washingMachine = WashingMachine()
    repeat(3) {
        launch { washingMachine.wash() }
    }
}