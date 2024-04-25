package single.table.inheritance.entity

abstract class TransportVehicle
protected constructor(
    manufacturer: String,
    model: String,
    val loadCapacity: Int
) : Vehicle(manufacturer, model)
