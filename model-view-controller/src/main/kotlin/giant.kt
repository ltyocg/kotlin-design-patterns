import io.github.oshai.kotlinlogging.KotlinLogging

class GiantController(
    private val giant: GiantModel,
    private val view: GiantView
) {
    var health by giant::health
    var fatigue by giant::fatigue
    var nourishment by giant::nourishment
    fun updateView() = view.displayGiant(giant)
}

class GiantView {
    private val logger = KotlinLogging.logger {}
    fun displayGiant(giant: GiantModel) = logger.info { giant }
}

data class GiantModel internal constructor(
    var health: Health,
    var fatigue: Fatigue,
    var nourishment: Nourishment
) {
    override fun toString(): String = "The giant looks $health, $fatigue and $nourishment."
}
