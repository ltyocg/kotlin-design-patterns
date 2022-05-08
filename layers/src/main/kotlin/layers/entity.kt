package layers

import jakarta.persistence.*

@Entity
class Cake(
    @Id
    @GeneratedValue
    var id: Long? = null,
    @OneToOne(cascade = [CascadeType.REMOVE])
    var topping: CakeTopping? = null,
    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.EAGER)
    var layers: MutableSet<CakeLayer> = mutableSetOf()
) {
    fun addLayer(layer: CakeLayer) {
        layers.add(layer)
    }

    override fun toString(): String = "id=$id topping=$topping layers=$layers"
}

@Entity
class CakeLayer(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String?,
    var calories: Int,
    @ManyToOne(cascade = [CascadeType.ALL])
    var cake: Cake? = null
) {
    override fun toString(): String = "id=$id name=$name calories=$calories"
}

@Entity
class CakeTopping(
    @Id
    @GeneratedValue
    var id: Long = 0,
    var name: String?,
    var calories: Int,
    @OneToOne(cascade = [CascadeType.ALL])
    var cake: Cake? = null
) {
    override fun toString(): String = "id=$id name=$name calories=$calories"
}