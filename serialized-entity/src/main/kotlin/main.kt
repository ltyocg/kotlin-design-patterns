import org.h2.jdbcx.JdbcDataSource

fun main() {
    val dataSource = JdbcDataSource().apply {
        setURL("jdbc:h2:~/test")
        execute("DROP TABLE WORLD IF EXISTS")
        execute("CREATE TABLE IF NOT EXISTS WORLD (ID INT PRIMARY KEY, COUNTRY BLOB)")
    }
    val serializedChina = CountryDao(Country(86, "China", "Asia", "Chinese"), dataSource)
    val serializedUnitedArabEmirates = CountryDao(Country(971, "United Arab Emirates", "Asia", "Arabic"), dataSource)
    serializedChina.insertCountry()
    serializedUnitedArabEmirates.insertCountry()
    serializedChina.selectCountry()
    serializedUnitedArabEmirates.selectCountry()
}

private fun JdbcDataSource.execute(sql: String) {
    connection.use { connection -> connection.createStatement().use { it.execute(sql) } }
}