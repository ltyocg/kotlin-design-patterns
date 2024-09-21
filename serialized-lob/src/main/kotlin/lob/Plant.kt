package lob

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.Serializable

class Plant(
    var name: String? = null,
    var type: String? = null,
) : Serializable {
    fun toXmlElement(xmlDoc: Document): Element? {
        xmlDoc.appendChild(xmlDoc.createElement(Plant::class.simpleName).apply {
            setAttribute("name", name)
            setAttribute("type", type)
        })
        return xmlDoc.documentElement
    }

    fun createObjectFromXml(node: Node) {
        val attributes = node.attributes
        name = attributes.getNamedItem("name").nodeValue
        type = attributes.getNamedItem("type").nodeValue
    }

    override fun toString(): String = "Name = $name,Type = $type"
}
