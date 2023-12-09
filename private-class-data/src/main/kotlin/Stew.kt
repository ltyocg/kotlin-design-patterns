import io.github.oshai.kotlinlogging.KotlinLogging

class Stew(
    numPotatoes: Int,
    numCarrots: Int,
    numMeat: Int,
    numPeppers: Int
) : ImmutableStew(numPotatoes, numCarrots, numMeat, numPeppers) {
    private val logger = KotlinLogging.logger {}
    override fun mix() =
        logger.info { "Mixing the stew we find: ${data.numPotatoes} potatoes, ${data.numCarrots} carrots, ${data.numMeat} meat and ${data.numPeppers} peppers" }

    fun taste() {
        logger.info { "Tasting the stew" }
        if (data.numPotatoes > 0) data.numPotatoes--
        if (data.numCarrots > 0) data.numCarrots--
        if (data.numMeat > 0) data.numMeat--
        if (data.numPeppers > 0) data.numPeppers--
    }
}
