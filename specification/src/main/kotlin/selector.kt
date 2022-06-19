import property.Color
import property.Mass
import property.Movement
import property.Size

sealed class AbstractSelector<T> : (T) -> Boolean {
    infix fun and(other: AbstractSelector<T>): AbstractSelector<T> = ConjunctionSelector(this, other)
    infix fun or(other: AbstractSelector<T>): AbstractSelector<T> = DisjunctionSelector(this, other)
    operator fun not(): AbstractSelector<T> = NegationSelector(this)
}

class ColorSelector(private val color: Color) : AbstractSelector<Creature>() {
    override fun invoke(t: Creature): Boolean = t.color == color
}

class ConjunctionSelector<T>
internal constructor(vararg selectors: AbstractSelector<T>) : AbstractSelector<T>() {
    private val leafComponents = listOf(*selectors)
    override fun invoke(t: T): Boolean = leafComponents.all { it(t) }
}

class DisjunctionSelector<T>
internal constructor(vararg selectors: AbstractSelector<T>) : AbstractSelector<T>() {
    private val leafComponents = listOf(*selectors)
    override fun invoke(t: T): Boolean = leafComponents.any { it(t) }
}

class MassEqualSelector(mass: Double) : AbstractSelector<Creature>() {
    private val mass = Mass(mass)
    override fun invoke(t: Creature): Boolean = t.mass == mass
}

class MassGreaterThanSelector(mass: Double) : AbstractSelector<Creature>() {
    private val mass = Mass(mass)
    override fun invoke(t: Creature): Boolean = t.mass > mass
}

class MassSmallerThanOrEqSelector(mass: Double) : AbstractSelector<Creature>() {
    private val mass = Mass(mass)
    override fun invoke(t: Creature): Boolean = t.mass <= mass
}

class MovementSelector(private val movement: Movement) : AbstractSelector<Creature>() {
    override fun invoke(t: Creature): Boolean = t.movement == movement
}

class NegationSelector<T>
internal constructor(private val component: AbstractSelector<T>) : AbstractSelector<T>() {
    override fun invoke(t: T): Boolean = !component(t)
}

class SizeSelector(private val size: Size) : AbstractSelector<Creature>() {
    override fun invoke(t: Creature): Boolean = t.size == size
}