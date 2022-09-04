import org.mockito.kotlin.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ArmsDealerTest {
    private val weapon1 = Weapon(1, "battle ram")
    private val weapon2 = Weapon(1, "wooden lance")
    private val context = mutableMapOf<String, MutableList<Weapon>>()
    private val weaponDatabase = mock<WeaponDatabase>()
    private val armsDealer = ArmsDealer(context, weaponDatabase)

    @Test
    fun `should save new student without writing to db`() {
        armsDealer.registerNew(weapon1)
        armsDealer.registerNew(weapon2)
        assertEquals(2, context[UnitActions.INSERT.actionValue]!!.size)
        verifyNoMoreInteractions(weaponDatabase)
    }

    @Test
    fun `should save deleted student without writing to db`() {
        armsDealer.registerDeleted(weapon1)
        armsDealer.registerDeleted(weapon2)
        assertEquals(2, context[UnitActions.DELETE.actionValue]!!.size)
        verifyNoMoreInteractions(weaponDatabase)
    }

    @Test
    fun `should save modified student without writing to db`() {
        armsDealer.registerModified(weapon1)
        armsDealer.registerModified(weapon2)
        assertEquals(2, context[UnitActions.MODIFY.actionValue]!!.size)
        verifyNoMoreInteractions(weaponDatabase)
    }

    @Test
    fun `should save all local changes to db`() {
        context[UnitActions.INSERT.actionValue] = mutableListOf(weapon1)
        context[UnitActions.MODIFY.actionValue] = mutableListOf(weapon1)
        context[UnitActions.DELETE.actionValue] = mutableListOf(weapon1)
        armsDealer.commit()
        verify(weaponDatabase, times(1)).insert(weapon1)
        verify(weaponDatabase, times(1)).modify(weapon1)
        verify(weaponDatabase, times(1)).delete(weapon1)
    }

    @Test
    fun `should not write to db if context is null`() {
        val weaponRepository = ArmsDealer(null, weaponDatabase)
        weaponRepository.commit()
        verifyNoMoreInteractions(weaponDatabase)
    }

    @Test
    fun `should not write to db if nothing to commit`() {
        val weaponRepository = ArmsDealer(mutableMapOf(), weaponDatabase)
        weaponRepository.commit()
        verifyNoMoreInteractions(weaponDatabase)
    }

    @Test
    fun `should not insert to db if no registered students to be committed`() {
        context[UnitActions.MODIFY.actionValue] = mutableListOf(weapon1)
        context[UnitActions.DELETE.actionValue] = mutableListOf(weapon1)
        armsDealer.commit()
        verify(weaponDatabase, never()).insert(weapon1)
    }

    @Test
    fun `should not modify to db if not registered students to be committed`() {
        context[UnitActions.INSERT.actionValue] = mutableListOf(weapon1)
        context[UnitActions.DELETE.actionValue] = mutableListOf(weapon1)
        armsDealer.commit()
        verify(weaponDatabase, never()).modify(weapon1)
    }

    @Test
    fun `should not delete from db if not registered students to be committed`() {
        context[UnitActions.INSERT.actionValue] = mutableListOf(weapon1)
        context[UnitActions.MODIFY.actionValue] = mutableListOf(weapon1)
        armsDealer.commit()
        verify(weaponDatabase, never()).delete(weapon1)
    }
}