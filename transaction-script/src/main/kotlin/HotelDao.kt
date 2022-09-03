import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

class HotelDao(private val dataSource: DataSource) {
    val all: Sequence<Room>
        get() {
            val connection = dataSource.connection
            val statement = connection.prepareStatement("SELECT * FROM ROOMS")
            val resultSet = statement.executeQuery()
            return sequence {
                while (resultSet.next()) yield(createRoom(resultSet))
                arrayOf(resultSet, statement, connection).forEach(AutoCloseable::close)
            }
        }

    fun getById(id: Int): Room? = preparedStatement("SELECT * FROM ROOMS WHERE ID = ?") {
        setInt(1, id)
        executeQuery().use { if (it.next()) createRoom(it) else null }
    }

    fun add(room: Room): Boolean = getById(room.id) == null && preparedStatement("INSERT INTO ROOMS VALUES (?,?,?,?)") {
        setInt(1, room.id)
        setString(2, room.roomType)
        setInt(3, room.price)
        setBoolean(4, room.booked)
        execute()
        true
    }

    fun update(room: Room): Boolean = preparedStatement("UPDATE ROOMS SET ROOM_TYPE = ?, PRICE = ?, BOOKED = ? WHERE ID = ?") {
        setString(1, room.roomType)
        setInt(2, room.price)
        setBoolean(3, room.booked)
        setInt(4, room.id)
        executeUpdate() > 0
    }

    fun delete(room: Room): Boolean = preparedStatement("DELETE FROM ROOMS WHERE ID = ?") {
        setInt(1, room.id)
        executeUpdate() > 0
    }

    private inline fun <R> preparedStatement(sql: String, block: PreparedStatement.() -> R): R = dataSource.connection.use { it.prepareStatement(sql).use(block) }
    private fun createRoom(resultSet: ResultSet): Room = Room(
        resultSet.getInt("ID"),
        resultSet.getString("ROOM_TYPE"),
        resultSet.getInt("PRICE"),
        resultSet.getBoolean("BOOKED")
    )
}