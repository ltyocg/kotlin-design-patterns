import org.slf4j.LoggerFactory

class ArmsDealer(
    private val context: MutableMap<String, MutableList<Weapon>>?,
    private val weaponDatabase: WeaponDatabase
) : IUnitOfWork<Weapon> {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun registerNew(entity: Weapon) {
        log.info("Registering {} for insert in context.", entity.name)
        register(entity, UnitActions.INSERT.actionValue)
    }

    override fun registerModified(entity: Weapon) {
        log.info("Registering {} for modify in context.", entity.name)
        register(entity, UnitActions.MODIFY.actionValue)
    }

    override fun registerDeleted(entity: Weapon) {
        log.info("Registering {} for delete in context.", entity.name)
        register(entity, UnitActions.DELETE.actionValue)
    }

    private fun register(weapon: Weapon, operation: String) {
        context!!.getOrPut(operation, ::mutableListOf).add(weapon)
    }

    override fun commit() {
        if (context.isNullOrEmpty()) return
        log.info("Commit started")
        if (context.containsKey(UnitActions.INSERT.actionValue)) {
            for (weapon in context[UnitActions.INSERT.actionValue]!!) {
                log.info("Inserting a new weapon {} to sales rack.", weapon.name)
                weaponDatabase.insert(weapon)
            }
        }
        if (context.containsKey(UnitActions.MODIFY.actionValue)) {
            for (weapon in context[UnitActions.MODIFY.actionValue]!!) {
                log.info("Scheduling {} for modification work.", weapon.name)
                weaponDatabase.modify(weapon)
            }
        }
        if (context.containsKey(UnitActions.DELETE.actionValue)) {
            for (weapon in context[UnitActions.DELETE.actionValue]!!) {
                log.info("Scrapping {}.", weapon.name)
                weaponDatabase.delete(weapon)
            }
        }
        log.info("Commit finished.")
    }
}