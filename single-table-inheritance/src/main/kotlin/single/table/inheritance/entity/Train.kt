package single.table.inheritance.entity

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue(value = "TRAIN")
class Train(
    manufacturer: String,
    model: String,
    noOfPassengers: Int,
    val noOfCarriages: Int
) : PassengerVehicle(manufacturer, model, noOfPassengers) {
    override fun toString(): String = "Train{${super.toString()}}"
}
