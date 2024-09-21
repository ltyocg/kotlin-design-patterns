package lob

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.Serializable

class Animal(
    var name: String? = null,
    val plantsEaten: MutableSet<Plant> = mutableSetOf(),
    val animalsEaten: MutableSet<Animal> = mutableSetOf()
) : Serializable {
    fun toXmlElement(xmlDoc: Document): Element? {
        val root = xmlDoc.createElement(Animal::class.simpleName)
        root.setAttribute("name", name)
        for (plant in plantsEaten) plant.toXmlElement(xmlDoc)?.also(root::appendChild)
        for (animal in animalsEaten) {
            val xmlElement = animal.toXmlElement(xmlDoc)
            if (xmlElement != null) {
                root.appendChild(xmlElement)
            }
        }
        xmlDoc.appendChild(root)
        return xmlDoc.firstChild as Element?
    }

    fun createObjectFromXml(node: Node) {
        name = node.attributes.getNamedItem("name").nodeValue
        val childNodes = node.childNodes
        iterateXmlForAnimalAndPlants(childNodes, animalsEaten, plantsEaten)
    }

    override fun toString(): String = buildString {
        appendLine()
        append("Animal Name = $name")
        if (animalsEaten.isNotEmpty()) {
            append("\n\tAnimals Eaten by $name: ")
            for (animal in animalsEaten) append("\n\t\t$animal")
        }
        appendLine()
        if (plantsEaten.isNotEmpty()) {
            append("\n\tPlants Eaten by $name: ")
            for (plant in plantsEaten) append("\n\t\t$plant")
        }
    }
}
