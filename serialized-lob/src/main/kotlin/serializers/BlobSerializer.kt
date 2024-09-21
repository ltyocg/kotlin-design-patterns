package serializers

import lob.Forest
import java.io.*

class BlobSerializer : LobSerializer("BINARY") {
    override fun serialize(toSerialize: Forest): Any = ByteArrayOutputStream().let {
        ObjectOutputStream(it).use { o -> o.writeObject(toSerialize) }
        ByteArrayInputStream(it.toByteArray())
    }

    override fun deSerialize(toDeserialize: Any?): Forest = ObjectInputStream(toDeserialize as InputStream).use {
        it.readObject() as Forest
    }
}
