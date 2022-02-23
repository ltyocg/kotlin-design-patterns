package com.ltyocg.iterator

import bst.BstIterator
import bst.TreeNode
import list.ItemType
import list.TreasureChest
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger("main")
private val TREASURE_CHEST = TreasureChest()
fun main() {
    arrayOf(ItemType.RING, ItemType.POTION, ItemType.WEAPON, ItemType.ANY).forEach(::demonstrateTreasureChestIteratorForType)
    demonstrateBstIterator()
}

private fun demonstrateTreasureChestIteratorForType(itemType: ItemType) {
    log.info("------------------------")
    log.info("Item Iterator for ItemType {}: ", itemType)
    TREASURE_CHEST.iterator(itemType).forEach { log.info(it.toString()) }
}

private fun demonstrateBstIterator() {
    log.info("------------------------")
    log.info("BST Iterator: ")
    BstIterator(TreeNode(8).apply {
        arrayOf(3, 10, 1, 6, 14, 4, 7, 13).forEach(::insert)
    }).forEach { log.info("Next node: {}", it.value) }
}