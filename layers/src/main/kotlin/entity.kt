import javax.persistence.*

@Entity
class Cake {
    @Id
    @GeneratedValue
    var id: Long? = null

    @OneToOne(cascade = [CascadeType.REMOVE])
    var topping: CakeTopping? = null

    @OneToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.EAGER)
    var layers: MutableSet<CakeLayer> = mutableSetOf()
    fun addLayer(layer: CakeLayer) {
        layers.add(layer)
    }

    override fun toString(): String = "id=$id topping=$topping layers=$layers"
}

@Entity
class CakeLayer(
    var name: String?,
    var calories: Int
) {
    constructor() : this(null, 0)

    @Id
    @GeneratedValue
    var id: Long? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    var cake: Cake? = null
    override fun toString(): String = "id=$id name=$name calories=$calories"
}

@Entity
class CakeTopping(
    var name: String?,
    var calories: Int
) {
    constructor() : this(null, 0)

    @Id
    @GeneratedValue
    var id: Long = 0

    @OneToOne(cascade = [CascadeType.ALL])
    var cake: Cake? = null
    override fun toString(): String = "id=$id name=$name calories=$calories"
}