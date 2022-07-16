class HalflingThief(private var method: StealingMethod) {
    fun steal() = method.steal()
    fun changeMethod(method: StealingMethod) {
        this.method = method
    }
}