import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
fun main() {
    logger.info { "Printing out eagerly initialized multiton contents" }
    logger.info { "KHAMUL=${Nazgul.getInstance(NazgulName.KHAMUL)}" }
    logger.info { "MURAZOR=${Nazgul.getInstance(NazgulName.MURAZOR)}" }
    logger.info { "DWAR=${Nazgul.getInstance(NazgulName.DWAR)}" }
    logger.info { "JI_INDUR=${Nazgul.getInstance(NazgulName.JI_INDUR)}" }
    logger.info { "AKHORAHIL=${Nazgul.getInstance(NazgulName.AKHORAHIL)}" }
    logger.info { "HOARMURATH=${Nazgul.getInstance(NazgulName.HOARMURATH)}" }
    logger.info { "ADUNAPHEL=${Nazgul.getInstance(NazgulName.ADUNAPHEL)}" }
    logger.info { "REN=${Nazgul.getInstance(NazgulName.REN)}" }
    logger.info { "UVATHA=${Nazgul.getInstance(NazgulName.UVATHA)}" }
    logger.info { "Printing out enum-based multiton contents" }
    logger.info { "KHAMUL=${NazgulEnum.KHAMUL}" }
    logger.info { "MURAZOR=${NazgulEnum.MURAZOR}" }
    logger.info { "DWAR=${NazgulEnum.DWAR}" }
    logger.info { "JI_INDUR=${NazgulEnum.JI_INDUR}" }
    logger.info { "AKHORAHIL=${NazgulEnum.AKHORAHIL}" }
    logger.info { "HOARMURATH=${NazgulEnum.HOARMURATH}" }
    logger.info { "ADUNAPHEL=${NazgulEnum.ADUNAPHEL}" }
    logger.info { "REN=${NazgulEnum.REN}" }
    logger.info { "UVATHA=${NazgulEnum.UVATHA}" }
}
