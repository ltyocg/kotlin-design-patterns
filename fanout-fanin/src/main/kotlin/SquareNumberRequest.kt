import kotlinx.coroutines.delay
import java.security.SecureRandom

class SquareNumberRequest(private val number: Long) {
    suspend fun delayedSquaring(consumer: Consumer) {
        delay(5000L + SecureRandom().nextInt(2000))
        consumer.add(number * number)
    }
}