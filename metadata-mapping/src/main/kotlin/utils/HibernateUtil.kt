package utils

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateUtil {
    val sessionFactory: SessionFactory = Configuration().configure().buildSessionFactory()
    fun shutdown() = sessionFactory.close()
}