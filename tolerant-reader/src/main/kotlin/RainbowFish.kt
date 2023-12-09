import java.io.Serializable

open class RainbowFish(
    val name: String,
    val age: Int,
    val lengthMeters: Int,
    val weightTons: Int
) : Serializable {
    override fun toString(): String = "name=$name age=$age length=$lengthMeters weight=$weightTons"
}
