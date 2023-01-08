import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

object Utils {
    fun executeSQL(sql: String, dataSource: DataSource) {
        dataSource.connection.use { it.createStatement().use { statement -> statement.executeUpdate(sql) } }
    }

    fun createSchema(dataSource: DataSource) {
        executeSQL(
            """
                CREATE TABLE CUSTOMERS (name varchar primary key, money decimal);
                CREATE TABLE PRODUCTS (name varchar primary key, price decimal, expiration_date date);
                CREATE TABLE PURCHASES (
                    product_name varchar references PRODUCTS(name),
                    customer_name varchar references CUSTOMERS(name)
                );
            """,
            dataSource
        )
    }

    fun deleteSchema(dataSource: DataSource) {
        executeSQL(
            """
                DROP TABLE PURCHASES IF EXISTS CASCADE;
                DROP TABLE CUSTOMERS IF EXISTS CASCADE;
                DROP TABLE PRODUCTS IF EXISTS CASCADE;
            """,
            dataSource
        )
    }

    fun createDataSource(): DataSource = JdbcDataSource().apply { setURL("jdbc:h2:~/test") }
}