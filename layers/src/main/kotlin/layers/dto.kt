package layers

class CakeInfo(
    val id: Long? = null,
    val cakeToppingInfo: CakeToppingInfo,
    val cakeLayerInfos: List<CakeLayerInfo>
) {
    fun calculateTotalCalories(): Int = cakeToppingInfo.calories + cakeLayerInfos.sumOf { it.calories }
    override fun toString(): String = "CakeInfo id=${id ?: -1} topping=$cakeToppingInfo layers=$cakeLayerInfos totalCalories=${calculateTotalCalories()}"
}

class CakeLayerInfo(
    val id: Long? = null,
    val name: String?,
    val calories: Int
) {
    override fun toString(): String = "CakeLayerInfo id=${id ?: -1} name=$name calories=$calories"
}

class CakeToppingInfo(
    val id: Long? = null,
    val name: String?,
    val calories: Int
) {
    override fun toString(): String = "CakeToppingInfo id=${id ?: -1} name=$name calories=$calories"
}