import java.util.*

interface Service {
    fun getWelcomeMessage(user: User): String
    val enhanced: Boolean
}

class PropertiesFeatureToggleVersion(properties: Properties) : Service {
    override val enhanced = try {
        properties["enhancedWelcome"] as Boolean
    } catch (e: Exception) {
        throw IllegalArgumentException("Invalid Enhancement Settings Provided.")
    }

    override fun getWelcomeMessage(user: User): String =
        if (enhanced) "Welcome $user. You're using the enhanced welcome message."
        else "Welcome to the application."
}

class TieredFeatureToggleVersion : Service {
    override fun getWelcomeMessage(user: User): String =
        if (UserGroup.isPaid(user)) "You're amazing $user. Thanks for paying for this awesome software."
        else "I suppose you can use this software."

    override val enhanced = true
}