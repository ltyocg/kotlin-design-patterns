import org.slf4j.LoggerFactory

class Stew(
    numPotatoes: Int,
    numCarrots: Int,
    numMeat: Int,
    numPeppers: Int
) : ImmutableStew(numPotatoes, numCarrots, numMeat, numPeppers) {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun mix() =
        log.info("Mixing the stew we find: {} potatoes, {} carrots, {} meat and {} peppers", data.numPotatoes, data.numCarrots, data.numMeat, data.numPeppers)

    fun taste() {
        log.info("Tasting the stew")
        if (data.numPotatoes > 0) data.numPotatoes--
        if (data.numCarrots > 0) data.numCarrots--
        if (data.numMeat > 0) data.numMeat--
        if (data.numPeppers > 0) data.numPeppers--
    }
}