package lob

import org.w3c.dom.Node
import org.w3c.dom.NodeList

internal fun iterateXmlForAnimalAndPlants(
    childNodes: NodeList,
    animalsEaten: MutableSet<Animal>,
    plantsEaten: MutableSet<Plant>
) {
    repeat(childNodes.length) {
        val child = childNodes.item(it)
        if (child.nodeType == Node.ELEMENT_NODE) when (child.nodeName) {
            Animal::class.simpleName -> animalsEaten.add(Animal().apply { createObjectFromXml(child) })
            Plant::class.simpleName -> plantsEaten.add(Plant().apply { createObjectFromXml(child) })
        }
    }
}
