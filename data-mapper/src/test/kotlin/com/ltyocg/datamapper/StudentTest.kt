package com.ltyocg.datamapper

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class StudentTest {
    @Test
    fun `test equality`() {
        val firstStudent = Student(1, "Adam", 'A')
        val secondStudent = Student(2, "Donald", 'B')
        assertEquals(firstStudent, firstStudent)
        assertNotEquals(firstStudent, secondStudent)
        assertEquals(secondStudent, Student(2, "Donald", 'B'))
    }
}