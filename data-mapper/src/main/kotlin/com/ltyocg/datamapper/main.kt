package com.ltyocg.datamapper

import org.slf4j.LoggerFactory

private const val STUDENT_STRING = "main(), student : "
private val log = LoggerFactory.getLogger("main")

fun main() {
    val mapper = StudentDataMapperImpl()
    var student = Student(1, "Adam", 'A')
    mapper.insert(student)
    log.debug("{}{}, is inserted", STUDENT_STRING, student)
    log.debug("{}{}, is searched", STUDENT_STRING, mapper.find(student.studentId))
    student = Student(student.studentId, "AdamUpdated", 'A')
    log.debug("{}{}, is updated", STUDENT_STRING, student)
    log.debug("{}{}, is going to be deleted", STUDENT_STRING, student)
    mapper.delete(student)
}