import io.github.oshai.kotlinlogging.KotlinLogging
import java.sql.*

object DataSource {
    private val logger = KotlinLogging.logger {}
    private lateinit var conn: Connection
    private lateinit var getschema: Statement
    private lateinit var deleteschema: Statement
    private lateinit var queryOrders: Statement
    private lateinit var insertIntoOrders: PreparedStatement
    private lateinit var removeorder: PreparedStatement
    private lateinit var queyOrderById: PreparedStatement

    init {
        try {
            conn = DriverManager.getConnection("jdbc:h2:mem:Embedded-Value")
            logger.info { "Connected to H2 in-memory database: ${conn.catalog}" }
        } catch (e: SQLException) {
            logger.error(e.cause) { e.localizedMessage }
        }
    }

    fun createSchema(): Boolean {
        try {
            conn.createStatement().use {
                it.execute(
                    """
                    |CREATE TABLE Orders (
                    |    Id INT AUTO_INCREMENT,
                    |    item VARCHAR(50) NOT NULL,
                    |    orderedBy VARCHAR(50),
                    |    city VARCHAR(50),
                    |    state VARCHAR(50),
                    |    pincode CHAR(6) NOT NULL,
                    |    PRIMARY KEY(Id)
                    |)
                    """.trimMargin()
                )
                insertIntoOrders = conn.prepareStatement("INSERT INTO Orders (item, orderedBy, city, state, pincode) VALUES(?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
                getschema = conn.createStatement()
                queryOrders = conn.createStatement()
                removeorder = conn.prepareStatement("DELETE FROM Orders WHERE Id = ?")
                queyOrderById = conn.prepareStatement("SELECT * FROM Orders WHERE Id = ?")
                deleteschema = conn.createStatement()
            }
        } catch (e: SQLException) {
            logger.error(e.cause) { e.localizedMessage }
            return false
        }
        return true
    }

    fun getSchema(): String {
        try {
            getschema.executeQuery("SHOW COLUMNS FROM Orders").use {
                return sequence {
                    while (it.next()) yield("Col name: ${it.getString(1)},  Col type: ${it.getString(2)}")
                }.joinToString("\n")
            }
        } catch (e: Exception) {
            logger.error(e.cause) { "Error in retrieving schema: ${e.localizedMessage}" }
        }
        return "Schema unavailable"
    }

    fun insertOrder(order: Order): Boolean {
        try {
            conn.autoCommit = false
            val address = order.shippingAddress
            if (insertIntoOrders.apply {
                    setString(1, order.item)
                    setString(2, order.orderedBy)
                    setString(3, address.city)
                    setString(4, address.state)
                    setString(5, address.pincode)
                }.executeUpdate() == 1) {
                val rs = insertIntoOrders.generatedKeys.also { it.last() }
                conn.commit()
                logger.info { "Inserted: ${order.copy(id = rs.getInt(1))}" }
            } else conn.rollback()
        } catch (e: Exception) {
            logger.error { e.localizedMessage }
        } finally {
            try {
                conn.autoCommit = true
            } catch (e: SQLException) {
                logger.error { e.localizedMessage }
            }
        }
        return true
    }

    fun queryOrders(): Sequence<Order> {
        try {
            queryOrders.executeQuery("SELECT * FROM Orders").use {
                return sequence {
                    while (it.next()) yield(
                        Order(
                            it.getInt(1),
                            it.getString(2),
                            it.getString(3),
                            ShippingAddress(
                                it.getString(4),
                                it.getString(5),
                                it.getString(6)
                            )
                        )
                    )
                }
            }
        } catch (e: SQLException) {
            logger.error(e.cause) { e.localizedMessage }
        }
        return emptySequence()
    }

    fun queryOrder(id: Int): Order? {
        queyOrderById.setInt(1, id)
        try {
            queyOrderById.executeQuery().use {
                if (it.next()) return Order(
                    it.getInt(1),
                    it.getString(2),
                    it.getString(3),
                    ShippingAddress(
                        it.getString(4),
                        it.getString(5),
                        it.getString(6)
                    )
                )
            }
        } catch (e: Exception) {
            logger.error(e.cause) { e.localizedMessage }
        }
        return null
    }

    fun removeOrder(id: Int) {
        try {
            conn.autoCommit = false
            removeorder.setInt(1, id)
            if (removeorder.executeUpdate() == 1) logger.info { "Order with id $id successfully removed" }
            else logger.info { "Order with id $id unavailable." }
        } catch (e: Exception) {
            logger.error(e.cause) { e.localizedMessage }
            conn.rollback()
        } finally {
            conn.autoCommit = true
        }
    }

    fun deleteSchema(): Boolean {
        try {
            deleteschema.execute("DROP TABLE Orders")
            queryOrders.close()
            queyOrderById.close()
            deleteschema.close()
            insertIntoOrders.close()
            conn.close()
            return true
        } catch (e: Exception) {
            logger.error(e.cause) { e.localizedMessage }
        }
        return false
    }
}