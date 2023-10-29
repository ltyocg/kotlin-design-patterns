sealed class Units(private vararg val children: Units) {
    open fun accept(visitor: UnitsVisitor) {
        children.forEach { it.accept(visitor) }
    }
}

class Commander(vararg children: Units) : Units(*children) {
    override fun accept(visitor: UnitsVisitor) {
        visitor.visitCommander(this)
        super.accept(visitor)
    }

    override fun toString(): String = "commander"
}

class Sergeant(vararg children: Units) : Units(*children) {
    override fun accept(visitor: UnitsVisitor) {
        visitor.visitSergeant(this)
        super.accept(visitor)
    }

    override fun toString(): String = "sergeant"
}

class Soldier(vararg children: Units) : Units(*children) {
    override fun accept(visitor: UnitsVisitor) {
        visitor.visitSoldier(this)
        super.accept(visitor)
    }

    override fun toString(): String = "soldier"
}
