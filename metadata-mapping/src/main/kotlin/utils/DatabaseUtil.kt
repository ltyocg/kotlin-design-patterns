package utils

import io.github.oshai.kotlinlogging.KotlinLogging
import org.h2.jdbcx.JdbcDataSource
import java.sql.SQLException

object DatabaseUtil {
    private val logger = KotlinLogging.logger {}
    fun init() {
        logger.info { "create h2 database" }
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
            logger.error(e) { "unable to create h2 data source" }
        }
    }
}
