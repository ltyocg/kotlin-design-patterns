import java.security.SecureRandom
import kotlin.math.abs

private val secureRandom = SecureRandom()
private const val ALL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"

fun generateId(): String = buildString {
    repeat(12) { append(ALL_CHARS[abs(secureRandom.nextInt() % ALL_CHARS.length)]) }
}