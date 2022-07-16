import org.slf4j.LoggerFactory
import javax.sql.DataSource

class UserTableModule(private val dataSource: DataSource) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun login(username: String?, password: String?): Int {
        dataSource.connection.use { connection ->
            connection.prepareStatement("select count(*) from USERS where username=? and password=?").use { preparedStatement ->
                var result = 0
                preparedStatement.setString(1, username)
                preparedStatement.setString(2, password)
                preparedStatement.executeQuery().use {
                    while (it.next()) result = it.getInt(1)
                }
                log.info(if (result == 1) "Login successfully!" else "Fail to login!")
                return result
            }
        }
    }

    fun registerUser(user: User): Int {
        dataSource.connection.use { connection ->
            connection.prepareStatement("insert into USERS (username, password) values (?,?)").use { it ->
                it.setString(1, user.username)
                it.setString(2, user.password)
                return it.executeUpdate()
                    .also { log.info("Register successfully!") }
            }
        }
    }

    companion object {
        const val CREATE_SCHEMA_SQL = "CREATE TABLE IF NOT EXISTS USERS (ID NUMBER, USERNAME VARCHAR(30) UNIQUE,PASSWORD VARCHAR(30))"
        const val DELETE_SCHEMA_SQL = "DROP TABLE USERS IF EXISTS"
    }
}