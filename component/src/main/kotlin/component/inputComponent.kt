package component

import GameObject
import org.slf4j.LoggerFactory
import java.awt.event.KeyEvent

interface InputComponent {
    fun update(gameObject: GameObject, e: Int)
}

class DemoInputComponent : InputComponent {
    private companion object {
        private val log = LoggerFactory.getLogger(DemoInputComponent::class.java)
    }

    override fun update(gameObject: GameObject, e: Int) {
        gameObject.updateVelocity(2)
        log.info("{} has moved right.", gameObject.name)
    }
}

class PlayerInputComponent : InputComponent {
    private companion object {
        private val log = LoggerFactory.getLogger(PlayerInputComponent::class.java)
        private const val WALK_ACCELERATION = 1
    }

    override fun update(gameObject: GameObject, e: Int) = when (e) {
        KeyEvent.KEY_LOCATION_LEFT -> {
            gameObject.updateVelocity(-WALK_ACCELERATION)
            log.info("{} has moved left.", gameObject.name)
        }

        KeyEvent.KEY_LOCATION_RIGHT -> {
            gameObject.updateVelocity(WALK_ACCELERATION)
            log.info("{} has moved right.", gameObject.name)
        }

        else -> {
            log.info("{}'s velocity is unchanged due to the invalid input", gameObject.name)
            gameObject.updateVelocity(0)
        }
    }
}