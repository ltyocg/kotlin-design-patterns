import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import kotlin.test.Test

sealed class UnitsTest<U : Units>(private val factory: (Array<Units>) -> U) {
    @Test
    fun accept() {
        val children = Array(5) { mock<Units>() }
        val units = factory(children)
        val visitor = mock<UnitsVisitor>()
        units.accept(visitor)
        verifyVisit(units, visitor)
        children.forEach { verify(it).accept(eq(visitor)) }
        verifyNoMoreInteractions(children)
        verifyNoMoreInteractions(visitor)
    }

    abstract fun verifyVisit(units: U, mockedVisitor: UnitsVisitor)

    class CommanderTest : UnitsTest<Commander>(::Commander) {
        override fun verifyVisit(units: Commander, mockedVisitor: UnitsVisitor) = verify(mockedVisitor).visitCommander(units)
    }

    class SergeantTest : UnitsTest<Sergeant>(::Sergeant) {
        override fun verifyVisit(units: Sergeant, mockedVisitor: UnitsVisitor) = verify(mockedVisitor).visitSergeant(units)
    }

    class SoldierTest : UnitsTest<Soldier>(::Soldier) {
        override fun verifyVisit(units: Soldier, mockedVisitor: UnitsVisitor) = verify(mockedVisitor).visitSoldier(units)
    }
}