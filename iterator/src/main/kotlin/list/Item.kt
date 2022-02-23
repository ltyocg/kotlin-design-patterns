package list

class Item(var type: ItemType, val name: String) {
    override fun toString(): String = name
}