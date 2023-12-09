import io.github.oshai.kotlinlogging.KotlinLogging

fun interface DragonSlayingStrategy {
    fun execute()
}

object LambdaStrategy {
    private val logger = KotlinLogging.logger {}

    enum class Strategy(private val dragonSlayingStrategy: DragonSlayingStrategy) : DragonSlayingStrategy {
        MeleeStrategy(DragonSlayingStrategy {
            logger.info { "With your Excalibur you severe the dragon's head!" }
        }),
        ProjectileStrategy(DragonSlayingStrategy {
            logger.info { "You shoot the dragon with the magical crossbow and it falls dead on the ground!" }
        }),
        SpellStrategy(DragonSlayingStrategy {
            logger.info { "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!" }
        });

        override fun execute() = dragonSlayingStrategy.execute()
    }
}

object MeleeStrategy : DragonSlayingStrategy {
    private val logger = KotlinLogging.logger {}
    override fun execute() = logger.info { "With your Excalibur you sever the dragon's head!" }
}

object ProjectileStrategy : DragonSlayingStrategy {
    private val logger = KotlinLogging.logger {}
    override fun execute() = logger.info { "You shoot the dragon with the magical crossbow and it falls dead on the ground!" }
}

object SpellStrategy : DragonSlayingStrategy {
    private val logger = KotlinLogging.logger {}
    override fun execute() = logger.info { "You cast the spell of disintegration and the dragon vaporizes in a pile of dust!" }
}
