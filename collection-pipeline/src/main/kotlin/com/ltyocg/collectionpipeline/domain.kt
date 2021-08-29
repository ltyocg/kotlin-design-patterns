package com.ltyocg.collectionpipeline

data class Car(
    val make: String,
    val model: String,
    val year: Int,
    val category: Category
)

data class Person(val cars: List<Car>)