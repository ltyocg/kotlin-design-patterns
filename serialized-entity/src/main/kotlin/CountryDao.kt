import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.sql.SQLException
import javax.sql.DataSource

class CountryDao(country: Country, private val dataSource: DataSource) {
    private val logger = KotlinLogging.logger {}
    private var country: Country

    init {
        this.country = country.copy()
    }

    fun insertCountry(): Int {
        try {
            dataSource.connection.use { connection ->
                connection.prepareStatement("INSERT INTO WORLD (ID, COUNTRY) VALUES (?, ?)").use { preparedStatement ->
                    ByteArrayOutputStream().use { byteArrayOutputStream ->
                        ObjectOutputStream(byteArrayOutputStream).use {
                            it.writeObject(country)
                            it.flush()
                            preparedStatement.setInt(1, country.code)
                            preparedStatement.setBlob(2, ByteArrayInputStream(byteArrayOutputStream.toByteArray()))
                            preparedStatement.execute()
                            return country.code
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            logger.info { "Exception thrown ${e.message}" }
        }
        return -1
    }

    fun selectCountry(): Int {
        try {
            dataSource.connection.use { connection ->
                connection.prepareStatement("SELECT ID, COUNTRY FROM WORLD WHERE ID = ?").use { preparedStatement ->
                    preparedStatement.setInt(1, country.code)
                    preparedStatement.executeQuery().use {
                        if (it.next()) {
                            val countryBlob = it.getBlob("country")
                            country = ObjectInputStream(ByteArrayInputStream(countryBlob.getBytes(1, countryBlob.length().toInt()))).readObject() as Country
                            logger.info { "Country: $country" }
                        }
                        return it.getInt("id")
                    }
                }
            }
        } catch (e: SQLException) {
            logger.info { "Exception thrown ${e.message}" }
        }
        return -1
    }
}
