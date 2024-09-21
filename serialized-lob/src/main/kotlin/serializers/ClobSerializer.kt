package serializers

import lob.Forest
import java.io.ByteArrayInputStream
import java.io.StringWriter
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

class ClobSerializer : LobSerializer("TEXT") {
    override fun serialize(forest: Forest): Any = StringWriter().apply sw@{
        TransformerFactory.newDefaultInstance().newTransformer().apply {
            setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no")
            setOutputProperty(OutputKeys.INDENT, "yes")
            transform(DOMSource(forest.toXmlElement()), StreamResult(this@sw))
        }
    }.toString()

    override fun deSerialize(toDeserialize: Any?): Forest = Forest().apply {
        createObjectFromXml(DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder().parse(ByteArrayInputStream(toDeserialize.toString().toByteArray())))
    }
}
