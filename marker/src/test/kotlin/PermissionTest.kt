import kotlin.reflect.full.findAnnotation
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class PermissionTest {
    @Test
    fun guard() {
        assertNotNull(Guard()::class.findAnnotation<Permission>())
    }

    @Test
    fun thief() = assertNull(Thief()::class.findAnnotation<Permission>())
}