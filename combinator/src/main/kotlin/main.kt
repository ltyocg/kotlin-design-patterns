import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}
val text = """
    |It was many and many a year ago,
    |In a kingdom by the sea,
    |That a maiden there lived whom you may know
    |By the name of ANNABEL LEE;
    |And this maiden she lived with no other thought
    |Than to love and be loved by me.
    |I was a child and she was a child,
    |In this kingdom by the sea;
    |But we loved with a love that was more than love-
    |I and my Annabel Lee;
    |With a love that the winged seraphs of heaven
    |Coveted her and me.
""".trimMargin()

fun main() {
    val queriesOr = arrayOf("many", "Annabel")
    logger.info { "the result of expanded(or) query[$queriesOr] is ${Finders.expandedFinder(*queriesOr).find(text)}" }
    val queriesAnd = arrayOf("Annabel", "my")
    logger.info { "the result of specialized(and) query[$queriesAnd] is ${Finders.specializedFinder(*queriesAnd).find(text)}" }
    logger.info { "the result of advanced query is ${Finders.advancedFinder("it was", "kingdom", "sea").find(text)}" }
    logger.info { "the result of filtered query is ${Finders.filteredFinder(" was ", "many", "child").find(text)}" }
}
