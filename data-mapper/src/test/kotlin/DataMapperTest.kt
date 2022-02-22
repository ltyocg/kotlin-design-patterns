import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DataMapperTest {
    @Test
    fun `test first data mapper`() {
        val mapper = StudentDataMapperImpl()
        val studentId = 1
        var student = Student(studentId, "Adam", 'A')
        mapper.insert(student)
        assertEquals(studentId, mapper.find(student.studentId)!!.studentId)
        val updatedName = "AdamUpdated"
        student = Student(student.studentId, updatedName, 'A')
        mapper.update(student)
        assertEquals(updatedName, mapper.find(student.studentId)!!.name)
        mapper.delete(student)
        assertNull(mapper.find(student.studentId))
    }
}