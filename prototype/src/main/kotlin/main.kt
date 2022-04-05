import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
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
    log.info(createMage().toString())
    log.info(createWarlord().toString())
    log.info(createBeast().toString())
}