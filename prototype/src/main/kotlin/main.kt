import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    HeroFactoryImpl(
        ElfMage("cooking"),
        ElfWarlord("cleaning"),
        ElfBeast("protecting")
    ).log()
    HeroFactoryImpl(
        OrcMage("axe"),
        OrcWarlord("sword"),
        OrcBeast("laser")
    ).log()
}

private fun HeroFactoryImpl.log() {
    logger.info { createMage() }
    logger.info { createWarlord() }
    logger.info { createBeast() }
}
