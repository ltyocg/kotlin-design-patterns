fun main() {
    GiantController(GiantModel(Health.HEALTHY, Fatigue.ALERT, Nourishment.SATURATED), GiantView()).apply {
        updateView()
        health = Health.WOUNDED
        nourishment = Nourishment.HUNGRY
        fatigue = Fatigue.TIRED
        updateView()
    }
}