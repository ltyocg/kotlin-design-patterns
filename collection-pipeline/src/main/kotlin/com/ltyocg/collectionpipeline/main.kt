package com.ltyocg.collectionpipeline

import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")

fun main() {
    val cars = CarFactory.createCars()
    log.info(FunctionalProgramming.getModelsAfter2000(cars).toString())
    log.info(FunctionalProgramming.getGroupingOfCarsByCategory(cars).toString())
    log.info(FunctionalProgramming.getSedanCarsOwnedSortedByDate(listOf(Person(cars))).toString())
}