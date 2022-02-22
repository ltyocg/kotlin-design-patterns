import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MessengerTest {
    private var stdOutBuffer = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        stdOutBuffer = ByteArrayOutputStream()
        System.setOut(PrintStream(stdOutBuffer))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(System.out)
    }

    @Test
    fun `test message from orcs`() {
        testMessage(Messenger().messageFromOrcs(), "Where there is a whip there is a way.")
    }

    @Test
    fun `test message from elves`() {
        testMessage(Messenger().messageFromElves(), "Much wind pours from your mouth.")
    }

    private fun testMessage(composedMessage: LetterComposite, message: String) {
        val words = message.split(" ")
        assertEquals(words.size, composedMessage.count())
        composedMessage.print()
        assertEquals(message, stdOutBuffer.toString().trim())
    }
}