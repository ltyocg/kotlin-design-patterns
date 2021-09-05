package com.ltyocg.converter

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val userConverter = UserConverter()
    val dtoUser = UserDto("John", "Doe", true, "whatever[at]wherever.com")
    val user = userConverter.convertFromDto(dtoUser)
    log.info("Entity converted from DTO: {}", user)
    val users = listOf(
        User("Camile", "Tough", false, "124sad"),
        User("Marti", "Luther", true, "42309fd"),
        User("Kate", "Smith", true, "if0243")
    )
    log.info("Domain entities:")
    users.asSequence().map(User::toString).forEach(log::info)
    log.info("DTO entities converted from domain:")
    userConverter.createFromEntities(users).map(UserDto::toString).forEach(log::info)
}