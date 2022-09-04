import org.slf4j.LoggerFactory

sealed class Entity(protected var id: Int) {
    protected open var position = 0
    abstract fun update()
}

class Skeleton(id: Int, public override var position: Int = 0) : Entity(id) {
    private companion object {
        private const val PATROLLING_LEFT_BOUNDING = 0
        private const val PATROLLING_RIGHT_BOUNDING = 100
    }

    private val log = LoggerFactory.getLogger(javaClass)
    var patrollingLeft = false
    override fun update() {
        if (patrollingLeft) {
            if (--position == PATROLLING_LEFT_BOUNDING) patrollingLeft = false
        } else if (++position == PATROLLING_RIGHT_BOUNDING) patrollingLeft = true
        log.info("Skeleton {} is on position {}.", id, position)
    }
}

class Statue(id: Int, var delay: Int = 0) : Entity(id) {
    private val log = LoggerFactory.getLogger(javaClass)
    var frames = 0
    override fun update() {
        if (++frames == delay) {
            log.info("Statue {} shoots lightning!", id)
            frames = 0
        }
    }
}