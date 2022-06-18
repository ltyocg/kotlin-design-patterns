abstract class Point<T>(var coordinateX: Int, var coordinateY: Int, val id: Int) {
    abstract fun move()
    abstract fun touches(obj: T): Boolean
    abstract fun handleCollision(toCheck: Collection<Point<*>>, all: MutableMap<Int, T>)
}