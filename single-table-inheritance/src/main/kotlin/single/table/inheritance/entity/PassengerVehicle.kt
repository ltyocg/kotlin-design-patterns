package single.table.inheritance.entity

abstract class PassengerVehicle
protected constructor(
    manufacturer: String,
    model: String,
    val noOfPassengers: Int
) : Vehicle(manufacturer, model) {
}
