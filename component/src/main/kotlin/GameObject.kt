import component.*

class GameObject(
    private val inputComponent: InputComponent,
    private val physicComponent: PhysicComponent,
    private val graphicComponent: GraphicComponent,
    val name: String

) {
    var velocity = 0
        private set
    var coordinate = 0
        private set

    fun demoUpdate() {
        inputComponent.update(this, 0)
        physicComponent.update(this)
        graphicComponent.update(this)
    }

    fun update(e: Int) {
        inputComponent.update(this, e)
        physicComponent.update(this)
        graphicComponent.update(this)
    }

    fun updateVelocity(acceleration: Int) {
        velocity += acceleration
    }

    fun updateCoordinate() {
        coordinate += velocity
    }

    companion object {
        fun createPlayer(): GameObject = GameObject(
            PlayerInputComponent(),
            ObjectPhysicComponent(),
            ObjectGraphicComponent(),
            "player"
        )

        fun createNpc(): GameObject = GameObject(
            DemoInputComponent(),
            ObjectPhysicComponent(),
            ObjectGraphicComponent(),
            "npc"
        )
    }
}