package repository

import jakarta.persistence.EntityManagerFactory
import org.apache.commons.dbcp2.BasicDataSource
import org.h2.Driver
import org.hibernate.dialect.H2Dialect
import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories("repository", repositoryBaseClass = SimpleJpaRepository::class)
@EnableTransactionManagement
class AppConfig {
    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager =
        JpaTransactionManager(entityManagerFactory)

    @Bean
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean =
        LocalContainerEntityManagerFactoryBean().apply {
            this.dataSource = dataSource
            setPackagesToScan("repository")
            persistenceProvider = HibernatePersistenceProvider()
            jpaPropertyMap = buildMap {
                put("hibernate.dialect", H2Dialect::class.qualifiedName)
                put("hibernate.hbm2ddl.auto", "create")
                put("hibernate.show_sql", false.toString())
            }
        }

    @Bean(destroyMethod = "close")
    fun dataSource(): BasicDataSource = BasicDataSource().apply {
        driver = Driver()
        url = "jdbc:h2:mem:databases-person"
        username = "sa"
        password = "sa"
    }
}
