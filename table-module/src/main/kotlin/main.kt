import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

fun main() {
    val dataSource = JdbcDataSource().apply { setURL("jdbc:h2:~/test") }
    dataSource.createSchema()
    val user1 = User(1, "123456", "123456")
    val user2 = User(2, "test", "password")
    with(UserTableModule(dataSource)) {
        registerUser(user1)
        login(user1.username, user1.password)
        login(user2.username, user2.password)
        registerUser(user2)
        login(user2.username, user2.password)
    }
    dataSource.deleteSchema()
}

private fun DataSource.deleteSchema() {
    connection.use { connection -> connection.createStatement().use { it.execute(UserTableModule.DELETE_SCHEMA_SQL) } }
}

private fun DataSource.createSchema() {
    connection.use { connection -> connection.createStatement().use { it.execute(UserTableModule.CREATE_SCHEMA_SQL) } }
}
