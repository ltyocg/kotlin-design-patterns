import io.github.oshai.kotlinlogging.KotlinLogging

class ArmsDealer(
    private val context: MutableMap<String, MutableList<Weapon>>?,
    private val weaponDatabase: WeaponDatabase
) : IUnitOfWork<Weapon> {
    private val logger = KotlinLogging.logger {}
    override fun registerNew(entity: Weapon) {
        logger.info { "Registering ${entity.name} for insert in context." }
        register(entity, UnitActions.INSERT.actionValue)
    }

    override fun registerModified(entity: Weapon) {
        logger.info { "Registering ${entity.name} for modify in context." }
        register(entity, UnitActions.MODIFY.actionValue)
    }

    override fun registerDeleted(entity: Weapon) {
        logger.info { "Registering ${entity.name} for delete in context." }
        register(entity, UnitActions.DELETE.actionValue)
    }

    private fun register(weapon: Weapon, operation: String) {
        context!!.getOrPut(operation, ::mutableListOf).add(weapon)
    }

    override fun commit() {
        if (context.isNullOrEmpty()) return
        logger.info { "Commit started" }
        if (context.containsKey(UnitActions.INSERT.actionValue)) {
            for (weapon in context[UnitActions.INSERT.actionValue]!!) {
                logger.info { "Inserting a new weapon ${weapon.name} to sales rack." }
                weaponDatabase.insert(weapon)
            }
        }
        if (context.containsKey(UnitActions.MODIFY.actionValue)) {
            for (weapon in context[UnitActions.MODIFY.actionValue]!!) {
                logger.info { "Scheduling ${weapon.name} for modification work." }
                weaponDatabase.modify(weapon)
            }
        }
        if (context.containsKey(UnitActions.DELETE.actionValue)) {
            for (weapon in context[UnitActions.DELETE.actionValue]!!) {
                logger.info { "Scrapping ${weapon.name}." }
                weaponDatabase.delete(weapon)
            }
        }
        logger.info { "Commit finished." }
    }
}
