import org.slf4j.LoggerFactory

open class ImmutableStew(numPotatoes: Int, numCarrots: Int, numMeat: Int, numPeppers: Int) {
    private val log = LoggerFactory.getLogger(javaClass)
    protected val data = StewData(numPotatoes, numCarrots, numMeat, numPeppers)
    open fun mix() =
        log.info("Mixing the immutable stew we find: {} potatoes, {} carrots, {} meat and {} peppers", data.numPotatoes, data.numCarrots, data.numMeat, data.numPeppers)
}