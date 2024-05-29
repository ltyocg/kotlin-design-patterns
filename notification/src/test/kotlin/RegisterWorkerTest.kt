import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegisterWorkerTest {
    @Test
    fun `run successfully`() {
        val registerWorker = RegisterWorker(
            RegisterWorkerDto(
                "name",
                "occupation",
                LocalDate.of(2000, 12, 1)
            )
        )
        registerWorker.run()
        assertFalse(registerWorker.notification.hasErrors())
    }

    @Test
    fun `run with missingName`() {
        val registerWorker = RegisterWorker(
            RegisterWorkerDto(
                occupation = "occupation",
                dateOfBirth = LocalDate.of(2000, 12, 1)
            )
        )
        registerWorker.run()
        assertTrue(registerWorker.notification.hasErrors())
        assertTrue(registerWorker.notification.errors.contains(RegisterWorkerDto.MISSING_NAME))
        assertEquals(registerWorker.notification.errors.size, 1)
    }

    @Test
    fun runWithMissingOccupation() {
        val registerWorker = RegisterWorker(
            RegisterWorkerDto(
                name = "name",
                dateOfBirth = LocalDate.of(2000, 12, 1)
            )
        )
        registerWorker.run()
        assertTrue(registerWorker.notification.hasErrors())
        assertTrue(registerWorker.notification.errors.contains(RegisterWorkerDto.MISSING_OCCUPATION))
        assertEquals(registerWorker.notification.errors.size, 1)
    }

    @Test
    fun `run with missing DOB`() {
        val registerWorker = RegisterWorker(RegisterWorkerDto("name", "occupation"))
        registerWorker.run()
        assertTrue(registerWorker.notification.hasErrors())
        assertTrue(registerWorker.notification.errors.contains(RegisterWorkerDto.MISSING_DOB))
        assertEquals(registerWorker.notification.errors.size, 2)
    }

    @Test
    fun `run with underage DOB`() {
        val registerWorker = RegisterWorker(
            RegisterWorkerDto(
                "name",
                "occupation",
                LocalDate.now().minusYears(17)
            )
        )
        registerWorker.run()
        assertTrue(registerWorker.notification.hasErrors())
        assertTrue(registerWorker.notification.errors.contains(RegisterWorkerDto.DOB_TOO_SOON))
        assertEquals(registerWorker.notification.errors.size, 1)
    }
}
