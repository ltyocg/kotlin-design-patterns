class RainbowFishV2(
    name: String,
    age: Int,
    lengthMeters: Int,
    weightTons: Int,
    val sleeping: Boolean = false,
    val hungry: Boolean = false,
    val angry: Boolean = false
) : RainbowFish(name, age, lengthMeters, weightTons)