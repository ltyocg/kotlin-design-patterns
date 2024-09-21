import org.h2.jdbcx.JdbcDataSource

class DatabaseService(private val dataTypeDb: String) {
    companion object {
        private val dataSource = JdbcDataSource().apply { setURL("jdbc:h2:~/test") }
    }

    fun shutDownService() {
        dataSource.connection.use { connection ->
            connection.createStatement().use { statement ->
                statement.execute("DROP TABLE FORESTS IF EXISTS")
            }
        }
    }

    fun startupService() {
        dataSource.connection.use { connection ->
            connection.createStatement().use {
                it.execute(
                    if (dataTypeDb == "BINARY") "CREATE TABLE IF NOT EXISTS FORESTS (ID NUMBER UNIQUE, NAME VARCHAR(30),FOREST VARBINARY)"
                    else "CREATE TABLE IF NOT EXISTS FORESTS (ID NUMBER UNIQUE, NAME VARCHAR(30),FOREST VARCHAR)"
                )
            }
        }
    }

    fun insert(id: Int, name: String?, data: Any?) {
        dataSource.connection.use { connection ->
            connection.prepareStatement("insert into FORESTS (id, name, forest) values (?,?,?)").use {
                it.setInt(1, id)
                it.setString(2, name)
                it.setObject(3, data)
                it.execute()
            }
        }
    }

    fun select(id: Long, columnsName: String?): Any? {
        dataSource.connection.use { connection ->
            connection.prepareStatement("select FOREST from FORESTS where id = ?").use { preparedStatement ->
                var result: Any? = null
                preparedStatement.setLong(1, id)
                preparedStatement.executeQuery().use {
                    while (it.next()) result = if (dataTypeDb == "BINARY") it.getBinaryStream(columnsName) else it.getString(columnsName)
                }
                return result
            }
        }
    }
}
