import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import spell.Spell
import spellbook.Spellbook
import wizard.Wizard

object HibernateUtil {
    private val logger = KotlinLogging.logger {}

    @Volatile
    private lateinit var sessionFactory: SessionFactory

    @Synchronized
    fun getSessionFactory(): SessionFactory {
        try {
            sessionFactory = Configuration()
                .addAnnotatedClass(Wizard::class.java)
                .addAnnotatedClass(Spellbook::class.java)
                .addAnnotatedClass(Spell::class.java)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.current_session_context_class", "thread")
                .setProperty("hibernate.show_sql", "false")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .buildSessionFactory()
        } catch (ex: Throwable) {
            logger.error(ex) { "Initial SessionFactory creation failed." }
            throw ExceptionInInitializerError(ex)
        }
        return sessionFactory
    }

    fun dropSession() = getSessionFactory().close()
}
