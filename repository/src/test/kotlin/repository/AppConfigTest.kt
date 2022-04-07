package repository

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [AppConfig::class])
class AppConfigTest {
    @Autowired
    private lateinit var dataSource: DataSource

    @Test
    @Transactional
    fun query() {
        val resultSet = dataSource.connection.createStatement().executeQuery("SELECT 1")
        lateinit var result: String
        while (resultSet.next()) result = resultSet.getString(1)
        assertEquals("1", result)
    }
}