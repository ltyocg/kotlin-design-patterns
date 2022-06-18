class Rect
internal constructor(var coordinateX: Double, var coordinateY: Double, var width: Double, var height: Double) {
    operator fun contains(p: Point<*>): Boolean =
        p.coordinateX >= coordinateX - width / 2 && p.coordinateX <= coordinateX + width / 2 && p.coordinateY >= coordinateY - height / 2 && p.coordinateY <= coordinateY + height / 2

    fun intersects(other: Rect): Boolean =
        !(coordinateX + width / 2 <= other.coordinateX - other.width / 2 || coordinateX - width / 2 >= other.coordinateX + other.width / 2 || coordinateY + height / 2 <= other.coordinateY - other.height / 2 || coordinateY - height / 2 >= other.coordinateY + other.height / 2)
}