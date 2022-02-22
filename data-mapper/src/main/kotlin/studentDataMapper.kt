interface StudentDataMapper {
    fun find(studentId: Int): Student?
    fun insert(student: Student)
    fun update(student: Student)
    fun delete(student: Student)
}

class StudentDataMapperImpl : StudentDataMapper {
    private val students = mutableListOf<Student>()
    override fun find(studentId: Int): Student? = students.firstOrNull { it.studentId == studentId }
    override fun insert(student: Student) {
        if (find(student.studentId) != null) throw DataMapperException("Student already [${student.name}] exists")
        students.add(student)
    }

    override fun update(student: Student) {
        val index = students.indexOf(student)
        if (index >= 0) students[index] = student
        else throw DataMapperException("Student [${student.name}] is not found")
    }

    override fun delete(student: Student) {
        if (!students.remove(student)) throw DataMapperException("Student already [${student.name}] exists")
    }
}

class DataMapperException(message: String?) : RuntimeException(message)