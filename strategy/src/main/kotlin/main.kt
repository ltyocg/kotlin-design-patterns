import io.github.oshai.kotlinlogging.KotlinLogging

private const val RED_DRAGON_EMERGES = "Red dragon emerges."
private const val GREEN_DRAGON_SPOTTED = "Green dragon spotted ahead!"
private const val BLACK_DRAGON_LANDS = "Black dragon lands before you."
private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { GREEN_DRAGON_SPOTTED }
    var dragonSlayer = DragonSlayer(MeleeStrategy)
    dragonSlayer.goToBattle()
    logger.info { RED_DRAGON_EMERGES }
    dragonSlayer.changeStrategy(ProjectileStrategy)
    dragonSlayer.goToBattle()
    logger.info { BLACK_DRAGON_LANDS }
    dragonSlayer.changeStrategy(SpellStrategy)
    dragonSlayer.goToBattle()
    logger.info { GREEN_DRAGON_SPOTTED }
    dragonSlayer = DragonSlayer { logger.info { "With your Excalibur you severe the dragon's head!" } }
    dragonSlayer.goToBattle()
    logger.info { RED_DRAGON_EMERGES }
    dragonSlayer.changeStrategy {
        logger.info { "You shoot the dragon with the magical crossbow and it falls dead on the ground!" }
    }
    dragonSlayer.goToBattle()
    logger.info { BLACK_DRAGON_LANDS }
    dragonSlayer.changeStrategy {
        logger.info { "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!" }
    }
    dragonSlayer.goToBattle()
    logger.info { GREEN_DRAGON_SPOTTED }
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.MeleeStrategy)
    dragonSlayer.goToBattle()
    logger.info { RED_DRAGON_EMERGES }
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.ProjectileStrategy)
    dragonSlayer.goToBattle()
    logger.info { BLACK_DRAGON_LANDS }
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.SpellStrategy)
    dragonSlayer.goToBattle()
}
