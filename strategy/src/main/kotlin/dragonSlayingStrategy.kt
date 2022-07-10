import org.slf4j.LoggerFactory

fun interface DragonSlayingStrategy {
    fun execute()
}

object LambdaStrategy {
    private val log = LoggerFactory.getLogger(javaClass)

    enum class Strategy(private val dragonSlayingStrategy: DragonSlayingStrategy) : DragonSlayingStrategy {
        MeleeStrategy(DragonSlayingStrategy {
            log.info("With your Excalibur you severe the dragon's head!")
        }),
        ProjectileStrategy(DragonSlayingStrategy {
            log.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!")
        }),
        SpellStrategy(DragonSlayingStrategy {
            log.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
        });

        override fun execute() = dragonSlayingStrategy.execute()
    }
}

object MeleeStrategy : DragonSlayingStrategy {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun execute() = log.info("With your Excalibur you sever the dragon's head!")
}

object ProjectileStrategy : DragonSlayingStrategy {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun execute() = log.info("You shoot the dragon with the magical crossbow and it falls dead on the ground!")
}

object SpellStrategy : DragonSlayingStrategy {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun execute() = log.info("You cast the spell of disintegration and the dragon vaporizes in a pile of dust!")
}