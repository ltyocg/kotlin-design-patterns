class QuadTree
internal constructor(private val boundary: Rect, private val capacity: Int) {
    private var divided = false
    private val points = mutableMapOf<Int, Point<*>>()
    private lateinit var northwest: QuadTree
    private lateinit var northeast: QuadTree
    private lateinit var southwest: QuadTree
    private lateinit var southeast: QuadTree
    fun insert(p: Point<*>) {
        if (p !in boundary) return
        if (points.size < capacity) points[p.id] = p
        else {
            if (!divided) divide()
            when (p) {
                in northwest.boundary -> northwest.insert(p)
                in northeast.boundary -> northeast.insert(p)
                in southwest.boundary -> southwest.insert(p)
                in southeast.boundary -> southeast.insert(p)
            }
        }
    }

    private fun divide() {
        val x = boundary.coordinateX
        val y = boundary.coordinateY
        val width = boundary.width
        val height = boundary.height
        fun quadTree(coordinateX: Double, coordinateY: Double) = QuadTree(Rect(coordinateX, coordinateY, width / 2, height / 2), capacity)
        northwest = quadTree(x - width / 4, y + height / 4)
        northeast = quadTree(x + width / 4, y + height / 4)
        southwest = quadTree(x - width / 4, y - height / 4)
        southeast = quadTree(x + width / 4, y - height / 4)
        divided = true
    }

    fun query(r: Rect, relevantPoints: MutableCollection<Point<*>>): Collection<Point<*>> {
        if (boundary.intersects(r)) {
            points.values
                .asSequence()
                .filter { it in r }
                .forEach { relevantPoints.add(it) }
            if (divided) {
                northwest.query(r, relevantPoints)
                northeast.query(r, relevantPoints)
                southwest.query(r, relevantPoints)
                southeast.query(r, relevantPoints)
            }
        }
        return relevantPoints
    }
}