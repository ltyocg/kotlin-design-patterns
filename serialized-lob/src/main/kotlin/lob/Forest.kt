package lob

import org.w3c.dom.Document
import org.w3c.dom.Element
import java.io.Serializable
import javax.xml.parsers.DocumentBuilderFactory

class Forest(
    var name: String? = null,
    val animals: MutableSet<Animal> = mutableSetOf(),
    val plants: MutableSet<Plant> = mutableSetOf()
) : Serializable {
    fun toXmlElement(): Element {
        val xmlDoc = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().newDocument()
        val forestXml = xmlDoc.createElement("Forest")
        forestXml.setAttribute("name", name)
        val animalsXml = xmlDoc.createElement("Animals")
        for (animal in animals) animalsXml.appendChild(animal.toXmlElement(xmlDoc))
        forestXml.appendChild(animalsXml)
        val plantsXml = xmlDoc.createElement("Plants")
        for (plant in plants) plantsXml.appendChild(plant.toXmlElement(xmlDoc))
        forestXml.appendChild(plantsXml)
        return forestXml
    }

    fun createObjectFromXml(document: Document) {
        name = document.documentElement.getAttribute("name")
        iterateXmlForAnimalAndPlants(document.getElementsByTagName("*"), animals, plants)
    }

    override fun toString(): String = buildString {
        fun appendElement(element: Any) {
            appendLine()
            appendLine("-".repeat(26))
            appendLine(element)
            appendLine("-".repeat(26))
        }
        appendLine("Forest Name = $name")
        appendLine("Animals found in the $name Forest: ")
        for (animal in animals) appendElement(animal)
        appendLine()
        appendLine("Plants in the $name Forest: ")
        for (plant in plants) appendElement(plant)
    }
}
