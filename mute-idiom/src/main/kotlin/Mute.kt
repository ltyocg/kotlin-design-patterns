object Mute {
    fun mute(runnable: () -> Unit) = try {
        runnable()
    } catch (e: Exception) {
        throw AssertionError(e)
    }

    fun loggedMute(runnable: () -> Unit) = try {
        runnable()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}