package com.ltyocg.extension.objects

open class Units(var name: String) {
    var unitsExtension: UnitsExtension? = null
    open fun getUnitsExtension(extensionName: String): UnitsExtension? = null
}

class CommanderUnits(name: String) : Units(name) {
    override fun getUnitsExtension(extensionName: String): UnitsExtension? =
        if (extensionName == "CommanderExtension") unitsExtension ?: CommanderExtension { Commander(this) }
        else super.getUnitsExtension(extensionName)
}

class SergeantUnits(name: String) : Units(name) {
    override fun getUnitsExtension(extensionName: String): UnitsExtension? =
        if (extensionName == "SergeantExtension") unitsExtension ?: SergeantExtension { Sergeant(this) }
        else super.getUnitsExtension(extensionName)
}

class SoldierUnits(name: String) : Units(name) {
    override fun getUnitsExtension(extensionName: String): UnitsExtension? =
        if (extensionName == "SoldierExtension") unitsExtension ?: SoldierExtension { Soldier(this) }
        else super.getUnitsExtension(extensionName)
}