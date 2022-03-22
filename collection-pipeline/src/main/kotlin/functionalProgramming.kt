fun List<Car>.getModelsAfter2000(): List<String> = asSequence()
    .filter { it.year > 2000 }
    .sortedBy { it.year }
    .map { it.model }
    .toList()

fun List<Car>.getGroupingOfCarsByCategory(): Map<Category, List<Car>> = groupBy { it.category }
fun List<Person>.getSedanCarsOwnedSortedByDate(): List<Car> = asSequence()
    .flatMap { it.cars }
    .filter { it.category == Category.SEDAN }
    .sortedBy { it.year }
    .toList()