import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() = with(Audio.INSTANCE) {
    playSound(getAudioStream("event-queue/src/main/resources/Bass-Drum-1.wav"), -10f)
    playSound(getAudioStream("event-queue/src/main/resources/Closed-Hi-Hat-1.wav"), -8f)
    logger.info { "Press Enter key to stop the program..." }
    System.`in`.read()
    stopService()
}
