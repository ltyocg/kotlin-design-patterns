import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
fun main() {
    with(Audio.INSTANCE) {
        playSound(getAudioStream("event-queue/src/main/resources/Bass-Drum-1.wav"), -10f)
        playSound(getAudioStream("event-queue/src/main/resources/Closed-Hi-Hat-1.wav"), -8f)
        log.info("Press Enter key to stop the program...")
        System.`in`.read()
        stopService()
    }
}