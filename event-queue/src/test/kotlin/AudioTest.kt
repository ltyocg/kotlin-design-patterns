import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AudioTest {
    private lateinit var audio: Audio

    @BeforeTest
    fun createAudioInstance() {
        audio = Audio()
    }

    @Test
    fun `test playSound`() = runBlocking {
        audio.playSound(audio.getAudioStream("src/main/resources/Bass-Drum-1.wav"), -10f)
        assertTrue(audio.isServiceRunning)
        delay(5000)
        audio.stopService()
        assertFalse(audio.isServiceRunning)
    }

    @Test
    fun `test queue`() = runBlocking {
        repeat(3) {
            audio.playSound(audio.getAudioStream("src/test/resources/Bass-Drum-1.aif"), -10f)
        }
        assertTrue(audio.pendingAudio.isNotEmpty())
        assertTrue(audio.isServiceRunning)
        delay(10000)
        audio.stopService()
        assertFalse(audio.isServiceRunning)
    }
}