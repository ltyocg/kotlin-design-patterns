import io.github.oshai.kotlinlogging.KotlinLogging

private const val STUDENT_STRING = "main(), student : "
private val logger = KotlinLogging.logger {}
fun main() {
    val mapper = StudentDataMapperImpl()
    var student = Student(1, "Adam", 'A')
    mapper.insert(student)
    logger.debug { "$STUDENT_STRING$student, is inserted" }
    logger.debug { "$STUDENT_STRING${mapper.find(student.studentId)}, is searched" }
    student = Student(student.studentId, "AdamUpdated", 'A')
    logger.debug { "$STUDENT_STRING$student, is updated" }
    logger.debug { "$STUDENT_STRING$student, is going to be deleted" }
    mapper.delete(student)
}
