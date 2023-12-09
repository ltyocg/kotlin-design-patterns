class RainbowFishV2(
    name: String,
    age: Int,
    lengthMeters: Int,
    weightTons: Int,
    val sleeping: Boolean = false,
    val hungry: Boolean = false,
    val angry: Boolean = false
) : RainbowFish(name, age, lengthMeters, weightTons) {
    override fun toString(): String = "name=$name age=$age length=$lengthMeters weight=$weightTons sleeping=$sleeping hungry=$hungry angry=$angry"
}
