class SpatialPartitionBubbles(
    private val bubbles: MutableMap<Int, Bubble>,
    private val bubblesQuadTree: QuadTree
) : SpatialPartitionGeneric<Bubble>() {
    override fun handleCollisionsUsingQt(obj: Bubble) {
        val quadTreeQueryResult = mutableListOf<Point<*>>()
        bubblesQuadTree.query(Rect(obj.coordinateX.toDouble(), obj.coordinateY.toDouble(), 2.0 * obj.radius, 2.0 * obj.radius), quadTreeQueryResult)
        obj.handleCollision(quadTreeQueryResult, bubbles)
    }
}