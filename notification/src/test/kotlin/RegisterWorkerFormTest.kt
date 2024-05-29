import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class RegisterWorkerFormTest {
    private lateinit var registerWorkerForm: RegisterWorkerForm

    @Test
    fun `submit successfully`() {
        registerWorkerForm = RegisterWorkerForm("John Doe", "Engineer", LocalDate.of(1990, 1, 1))
        assertNull(registerWorkerForm.worker)
        registerWorkerForm.submit()
        assertNotNull(registerWorkerForm.worker)
        assertEquals("John Doe", registerWorkerForm.worker!!.name)
        assertEquals("Engineer", registerWorkerForm.worker!!.occupation)
        assertEquals(LocalDate.of(1990, 1, 1), registerWorkerForm.worker!!.dateOfBirth)
    }

    @Test
    fun `submit with errors`() {
        registerWorkerForm = RegisterWorkerForm(null, null, null)
        registerWorkerForm.submit()
        assertNull(registerWorkerForm.worker!!.name)
        assertNull(registerWorkerForm.worker!!.occupation)
        assertNull(registerWorkerForm.worker!!.dateOfBirth)
        assertEquals(registerWorkerForm.worker!!.notification.errors.size, 3)
    }
}
