object FunctionalProgramming {
    fun getModelsAfter2000(cars: List<Car>): List<String> = cars.asSequence()
        .filter { it.year > 2000 }
        .sortedBy { it.year }
        .map { it.model }
        .toList()

    fun getGroupingOfCarsByCategory(cars: List<Car>): Map<Category, List<Car>> = cars.groupBy { it.category }
    fun getSedanCarsOwnedSortedByDate(persons: List<Person>): List<Car> = persons.asSequence()
        .flatMap { it.cars }
        .filter { it.category == Category.SEDAN }
        .sortedBy { it.year }
        .toList()
}