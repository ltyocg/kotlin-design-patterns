package com.ltyocg.converter

import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ConverterTest {
    private val userConverter = UserConverter()

    @Test
    fun `test conversions starting from domain`() {
        val u1 = User("Tom", "Hanks", true, "tom@hanks.com")
        assertEquals(u1, userConverter.convertFromDto(userConverter.convertFromEntity(u1)))
    }

    @Test
    fun `test conversions starting from dto`() {
        val u1 = UserDto("Tom", "Hanks", true, "tom@hanks.com")
        assertEquals(u1, userConverter.convertFromEntity(userConverter.convertFromDto(u1)))
    }

    @Test
    fun `test custom converter`() {
        assertEquals("johndoe@whatever.com", Converter<UserDto, User>(
            { User(it.firstName, it.lastName, it.active, Random.nextInt().toString()) },
            { UserDto(it.firstName, it.lastName, it.active, "${it.firstName.lowercase()}${it.lastName.lowercase()}@whatever.com") }
        ).convertFromEntity(User("John", "Doe", false, "12324")).email)
    }

    @Test
    fun `test collection conversion`() {
        val users = listOf(
            User("Camile", "Tough", false, "124sad"),
            User("Marti", "Luther", true, "42309fd"),
            User("Kate", "Smith", true, "if0243")
        )
        assertContentEquals(users, userConverter.createFromDtos(userConverter.createFromEntities(users)))
    }
}