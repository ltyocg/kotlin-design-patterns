package single.table.inheritance.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(value = "TRUCK")
class Truck(
    manufacturer: String,
    model: String,
    loadCapacity: Int,
    val towingCapacity: Int
) : TransportVehicle(manufacturer, model, loadCapacity) {
    override fun toString(): String = "Truck{ ${super.toString()}, towingCapacity=$towingCapacity}"
}
