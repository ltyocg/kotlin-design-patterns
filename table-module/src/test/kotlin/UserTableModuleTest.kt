import org.h2.jdbcx.JdbcDataSource
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.sql.DriverManager
import java.sql.SQLException
import javax.sql.DataSource

internal class UserTableModuleTest {
    @BeforeEach
    @Throws(SQLException::class)
    fun setUp() {
        DriverManager.getConnection(DB_URL).use { connection ->
            connection.createStatement().use { statement ->
                statement.execute(UserTableModule.DELETE_SCHEMA_SQL)
                statement.execute(UserTableModule.CREATE_SCHEMA_SQL)
            }
        }
    }

    @AfterEach
    @Throws(SQLException::class)
    fun tearDown() {
        DriverManager.getConnection(DB_URL).use { connection -> connection.createStatement().use { statement -> statement.execute(UserTableModule.DELETE_SCHEMA_SQL) } }
    }

    @Test
    @Throws(SQLException::class)
    fun loginShouldFail() {
        val dataSource = createDataSource()
        val userTableModule = UserTableModule(dataSource)
        val (_, username, password) = User(1, "123456", "123456")
        Assertions.assertEquals(
            0, userTableModule.login(
                username!!,
                password!!
            )
        )
    }

    @Test
    @Throws(SQLException::class)
    fun loginShouldSucceed() {
        val dataSource = createDataSource()
        val userTableModule = UserTableModule(dataSource)
        val user = User(1, "123456", "123456")
        userTableModule.registerUser(user)
        Assertions.assertEquals(
            1, userTableModule.login(
                user.username!!,
                user.password!!
            )
        )
    }

    @Test
    @Throws(SQLException::class)
    fun registerShouldFail() {
        val dataSource = createDataSource()
        val userTableModule = UserTableModule(dataSource)
        val user = User(1, "123456", "123456")
        userTableModule.registerUser(user)
        Assertions.assertThrows(SQLException::class.java) { userTableModule.registerUser(user) }
    }

    @Test
    @Throws(SQLException::class)
    fun registerShouldSucceed() {
        val dataSource = createDataSource()
        val userTableModule = UserTableModule(dataSource)
        val user = User(1, "123456", "123456")
        Assertions.assertEquals(1, userTableModule.registerUser(user))
    }

    companion object {
        private const val DB_URL = "jdbc:h2:~/test"
        private fun createDataSource(): DataSource {
            val dataSource = JdbcDataSource()
            dataSource.setURL(DB_URL)
            return dataSource
        }
    }
}