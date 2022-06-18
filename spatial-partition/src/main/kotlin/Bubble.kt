import org.slf4j.LoggerFactory
import java.security.SecureRandom

class Bubble internal constructor(x: Int, y: Int, id: Int, val radius: Int) : Point<Bubble>(x, y, id) {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun move() {
        coordinateX += RANDOM.nextInt(3) - 1
        coordinateY += RANDOM.nextInt(3) - 1
    }

    override fun touches(obj: Bubble): Boolean =
        (coordinateX - obj.coordinateX) * (coordinateX - obj.coordinateX) + (coordinateY - obj.coordinateY) * (coordinateY - obj.coordinateY) <= (radius + obj.radius) * (radius + obj.radius)

    override fun handleCollision(toCheck: Collection<Point<*>>, all: MutableMap<Int, Bubble>) {
        var toBePopped = false
        for (point in toCheck) {
            val otherId = point.id
            all[otherId]?.also {
                if (id != otherId && touches(it)) {
                    it.pop(all)
                    toBePopped = true
                }
            }
        }
        if (toBePopped) pop(all)
    }

    fun pop(allBubbles: MutableMap<Int, Bubble>) {
        log.info("Bubble {} popped at ({},{})!", id, coordinateX, coordinateY)
        allBubbles.remove(id)
    }

    private companion object {
        private val RANDOM = SecureRandom()
    }
}