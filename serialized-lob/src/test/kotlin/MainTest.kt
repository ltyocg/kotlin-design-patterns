import lob.Animal
import lob.Forest
import lob.Plant
import org.xml.sax.SAXException
import serializers.BlobSerializer
import serializers.ClobSerializer
import java.io.IOException
import java.sql.SQLException
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException
import kotlin.test.Test
import kotlin.test.assertEquals

class MainTest {
    @Test
    fun `should execute without exception clob`() = main(arrayOf("CLOB"))

    @Test
    fun `should execute without exception blob`() = main(emptyArray())

    @Test
    fun clobSerializer() {
        val forest = createForest()
        try {
            ClobSerializer().use { serializer ->
                assertEquals(
                    forest.hashCode(),
                    serializer.deSerialize(
                        serializer.loadFromDb(serializer.persistToDb(1, forest.name, serializer.serialize(forest)), Forest::class.simpleName)
                    ).hashCode(),
                    "Hashes of objects after Serializing and Deserializing are the same"
                )
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: TransformerException) {
            throw RuntimeException(e)
        } catch (e: ParserConfigurationException) {
            throw RuntimeException(e)
        } catch (e: SAXException) {
            throw RuntimeException(e)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }
    }

    @Test
    fun blobSerializer() {
        val forest = createForest()
        try {
            BlobSerializer().use {
                assertEquals(
                    forest.hashCode(),
                    it.deSerialize(
                        it.loadFromDb(it.persistToDb(1, forest.name, it.serialize(forest)), Forest::class.simpleName)
                    ).hashCode(),
                    "Hashes of objects after Serializing and Deserializing are the same"
                )
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        } catch (e: TransformerException) {
            throw RuntimeException(e)
        } catch (e: ParserConfigurationException) {
            throw RuntimeException(e)
        } catch (e: SAXException) {
            throw RuntimeException(e)
        } catch (e: ClassNotFoundException) {
            throw RuntimeException(e)
        }
    }

    private fun createForest(): Forest {
        val grass = Plant("Grass", "Herb")
        val zebra = Animal("Zebra", mutableSetOf(grass), mutableSetOf())
        val buffalo = Animal("Buffalo", mutableSetOf(grass), mutableSetOf())
        return Forest(
            "Amazon",
            mutableSetOf(Animal("Lion", mutableSetOf(), mutableSetOf(zebra, buffalo)), buffalo, zebra),
            mutableSetOf(grass, Plant("Oak", "Tree"))
        )
    }
}
