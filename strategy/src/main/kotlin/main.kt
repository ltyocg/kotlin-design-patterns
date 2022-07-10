import org.slf4j.LoggerFactory

private const val RED_DRAGON_EMERGES = "Red dragon emerges."
private const val GREEN_DRAGON_SPOTTED = "Green dragon spotted ahead!"
private const val BLACK_DRAGON_LANDS = "Black dragon lands before you."
private val log = LoggerFactory.getLogger("main")
fun main() {
    log.info(GREEN_DRAGON_SPOTTED)
    var dragonSlayer = DragonSlayer(MeleeStrategy)
    dragonSlayer.goToBattle()
    log.info(RED_DRAGON_EMERGES)
    dragonSlayer.changeStrategy(ProjectileStrategy)
    dragonSlayer.goToBattle()
    log.info(BLACK_DRAGON_LANDS)
    dragonSlayer.changeStrategy(SpellStrategy)
    dragonSlayer.goToBattle()
    log.info(GREEN_DRAGON_SPOTTED)
    dragonSlayer = DragonSlayer { log.info("With your Excalibur you severe the dragon's head!") }
    dragonSlayer.goToBattle()
    log.info(RED_DRAGON_EMERGES)
    dragonSlayer.changeStrategy {
        log.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!")
    }
    dragonSlayer.goToBattle()
    log.info(BLACK_DRAGON_LANDS)
    dragonSlayer.changeStrategy {
        log.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
    }
    dragonSlayer.goToBattle()
    log.info(GREEN_DRAGON_SPOTTED)
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.MeleeStrategy)
    dragonSlayer.goToBattle()
    log.info(RED_DRAGON_EMERGES)
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.ProjectileStrategy)
    dragonSlayer.goToBattle()
    log.info(BLACK_DRAGON_LANDS)
    dragonSlayer.changeStrategy(LambdaStrategy.Strategy.SpellStrategy)
    dragonSlayer.goToBattle()
}