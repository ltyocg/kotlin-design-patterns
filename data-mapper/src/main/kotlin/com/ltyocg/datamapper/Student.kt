package com.ltyocg.datamapper

data class Student(
    val studentId: Int,
    val name: String,
    val grade: Char
) {
    override fun equals(other: Any?): Boolean = this === other || other is Student && studentId == other.studentId
    override fun hashCode(): Int = studentId
}
