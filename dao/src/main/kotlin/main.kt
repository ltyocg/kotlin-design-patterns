import io.github.oshai.kotlinlogging.KotlinLogging
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

const val DB_URL = "jdbc:h2:~/dao"
private const val ALL_CUSTOMERS = "customerDao.all: "
private val logger = KotlinLogging.logger {}
fun main() {
    performOperationsUsing(InMemoryCustomerDao())
    val dataSource = JdbcDataSource().apply { setURL(DB_URL) }
    executeSql(dataSource, CustomerSchemaSql.CREATE_SCHEMA_SQL)
    performOperationsUsing(DbCustomerDao(dataSource))
    executeSql(dataSource, CustomerSchemaSql.DELETE_SCHEMA_SQL)
}

private fun performOperationsUsing(customerDao: CustomerDao) {
    listOf(
        Customer(1, "Adam", "Adamson"),
        Customer(2, "Bob", "Bobson"),
        Customer(3, "Carl", "Carlson")
    ).forEach(customerDao::add)
    logger.info { ALL_CUSTOMERS }
    customerDao.all.forEach { logger.info { it } }
    logger.info { "customerDao.getById(2): ${customerDao.getById(2)}" }
    val customer = Customer(4, "Dan", "Danson")
    customerDao.add(customer)
    logger.info { ALL_CUSTOMERS + customerDao.all }
    customer.firstName = "Daniel"
    customer.lastName = "Danielson"
    customerDao.update(customer)
    logger.info { ALL_CUSTOMERS }
    customerDao.all.forEach { logger.info { it } }
    customerDao.delete(customer)
    logger.info { ALL_CUSTOMERS + customerDao.all }
}

private fun executeSql(dataSource: DataSource, sql: String) {
    dataSource.connection.use { it.createStatement().use { statement -> statement.execute(sql) } }
}
