package layers

import org.h2.jdbcx.JdbcDataSource
import org.hibernate.dialect.H2Dialect
import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory

@Configuration
@EnableJpaRepositories("layers")
@ComponentScan("layers.dao")
@EnableTransactionManagement
open class ApplicationConfig {
    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager =
        JpaTransactionManager(entityManagerFactory)

    @Bean
    open fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            dataSource = JdbcDataSource().apply {
                setUrl("jdbc:h2:~/databases/cake")
                user = "sa"
                password = "sa"
            }
            setPackagesToScan("layers")
            persistenceProvider = HibernatePersistenceProvider()
            jpaPropertyMap = buildMap {
                put("hibernate.dialect", H2Dialect::class.qualifiedName)
                put("hibernate.hbm2ddl.auto", "create-drop")
                put("hibernate.show_sql", false.toString())
            }
        }
}