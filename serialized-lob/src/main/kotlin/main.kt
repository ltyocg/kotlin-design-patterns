import io.github.oshai.kotlinlogging.KotlinLogging
import lob.Animal
import lob.Forest
import lob.Plant
import org.xml.sax.SAXException
import serializers.BlobSerializer
import serializers.ClobSerializer
import serializers.LobSerializer
import java.io.IOException
import java.sql.SQLException
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.TransformerException

private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) = executeSerializer(
    createForest(),
    if (args.isNotEmpty() && args[0] == "CLOB") ClobSerializer() else BlobSerializer()
)

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

private fun executeSerializer(forest: Forest, lobSerializer: LobSerializer) {
    try {
        lobSerializer.use {
            logger.info { it.deSerialize(it.loadFromDb(it.persistToDb(1, forest.name, it.serialize(forest)), Forest::class.simpleName)) }
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
