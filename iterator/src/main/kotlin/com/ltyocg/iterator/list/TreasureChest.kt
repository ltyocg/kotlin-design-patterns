package com.ltyocg.iterator.list

class TreasureChest {
    private val _items = listOf(
        Item(ItemType.POTION, "Potion of courage"),
        Item(ItemType.RING, "Ring of shadows"),
        Item(ItemType.POTION, "Potion of wisdom"),
        Item(ItemType.POTION, "Potion of blood"),
        Item(ItemType.WEAPON, "Sword of silver +1"),
        Item(ItemType.POTION, "Potion of rust"),
        Item(ItemType.POTION, "Potion of healing"),
        Item(ItemType.RING, "Ring of armor"),
        Item(ItemType.WEAPON, "Steel halberd"),
        Item(ItemType.WEAPON, "Dagger of poison")
    )

    fun iterator(itemType: ItemType): Iterator<Item> = _items.asSequence().filter { it.type == ItemType.ANY || it.type == itemType }.iterator()
    val items: List<Item>
        get() = _items.toList()
}