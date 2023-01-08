package utils

import org.h2.jdbcx.JdbcDataSource
import org.slf4j.LoggerFactory
import java.sql.SQLException

object DatabaseUtil {
    private val log = LoggerFactory.getLogger(javaClass)
    fun init() {
        log.info("create h2 database")
        val source = JdbcDataSource().apply { setURL("jdbc:h2:mem:metamapping") }
        try {
            source.connection.createStatement().use {
                it.execute(
                    """
                    DROP TABLE IF EXISTS `user_account`;
                    CREATE TABLE `user_account` (
                    `id` int(11) NOT NULL AUTO_INCREMENT,
                    `username` varchar(255) NOT NULL,
                    `password` varchar(255) NOT NULL,
                    PRIMARY KEY (`id`));
                    """.trimIndent()
                )
            }
        } catch (e: SQLException) {
            log.error("unable to create h2 data source", e)
        }
    }
}