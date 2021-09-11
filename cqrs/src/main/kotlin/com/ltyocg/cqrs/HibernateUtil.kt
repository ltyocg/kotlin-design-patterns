package com.ltyocg.cqrs

import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

object HibernateUtil {
    lateinit var sessionFactory: SessionFactory
        private set

    fun init() {
        sessionFactory = MetadataSources(StandardServiceRegistryBuilder().configure().build()).buildMetadata().buildSessionFactory()
    }
}