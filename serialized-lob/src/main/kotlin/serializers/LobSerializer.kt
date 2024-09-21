package serializers

import DatabaseService
import lob.Forest
import java.io.Closeable
import java.io.Serializable
import java.sql.SQLException

abstract class LobSerializer
protected constructor(dataTypeDb: String) : Serializable, Closeable {
    @Transient
    private val databaseService = DatabaseService(dataTypeDb).apply { startupService() }
    abstract fun serialize(toSerialize: Forest): Any
    fun persistToDb(id: Int, name: String?, data: Any?): Int {
        databaseService.insert(id, name, data)
        return id
    }

    fun loadFromDb(id: Int, columnName: String?): Any? = databaseService.select(id.toLong(), columnName)
    abstract fun deSerialize(toDeserialize: Any?): Forest
    override fun close() = try {
        databaseService.shutDownService()
    } catch (e: SQLException) {
        throw RuntimeException(e)
    }
}
