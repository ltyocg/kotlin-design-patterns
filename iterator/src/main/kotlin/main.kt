package com.ltyocg.iterator

import bst.BstIterator
import bst.TreeNode
import io.github.oshai.kotlinlogging.KotlinLogging
import list.ItemType
import list.TreasureChest

private val logger = KotlinLogging.logger {}
private val TREASURE_CHEST = TreasureChest()
fun main() {
    arrayOf(ItemType.RING, ItemType.POTION, ItemType.WEAPON, ItemType.ANY).forEach(::demonstrateTreasureChestIteratorForType)
    demonstrateBstIterator()
}

private fun demonstrateTreasureChestIteratorForType(itemType: ItemType) {
    logger.info { "------------------------" }
    logger.info { "Item Iterator for ItemType $itemType: " }
    TREASURE_CHEST.iterator(itemType).forEach { logger.info { it } }
}

private fun demonstrateBstIterator() {
    logger.info { "------------------------" }
    logger.info { "BST Iterator: " }
    BstIterator(TreeNode(8).apply {
        arrayOf(3, 10, 1, 6, 14, 4, 7, 13).forEach(::insert)
    }).forEach { logger.info { "Next node: ${it.value}" } }
}
