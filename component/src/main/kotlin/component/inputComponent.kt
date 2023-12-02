package component

import GameObject
import io.github.oshai.kotlinlogging.KotlinLogging
import java.awt.event.KeyEvent

interface InputComponent {
    fun update(gameObject: GameObject, e: Int)
}

class DemoInputComponent : InputComponent {
    private val logger = KotlinLogging.logger {}
    override fun update(gameObject: GameObject, e: Int) {
        gameObject.updateVelocity(2)
        logger.info { "${gameObject.name} has moved right." }
    }
}

class PlayerInputComponent : InputComponent {
    private val logger = KotlinLogging.logger {}
    override fun update(gameObject: GameObject, e: Int) = when (e) {
        KeyEvent.KEY_LOCATION_LEFT -> {
            gameObject.updateVelocity(-WALK_ACCELERATION)
            logger.info { "${gameObject.name} has moved left." }
        }

        KeyEvent.KEY_LOCATION_RIGHT -> {
            gameObject.updateVelocity(WALK_ACCELERATION)
            logger.info { "${gameObject.name} has moved right." }
        }

        else -> {
            logger.info { "${gameObject.name}'s velocity is unchanged due to the invalid input" }
            gameObject.updateVelocity(0)
        }
    }

    private companion object {
        private const val WALK_ACCELERATION = 1
    }
}
