import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.sql.PreparedStatement
import javax.sql.DataSource

interface CustomerDao {
    fun findByName(name: String): Customer?
    fun update(customer: Customer)
    fun save(customer: Customer)
    fun addProduct(product: Product, customer: Customer)
    fun deleteProduct(product: Product, customer: Customer)
}

class CustomerDaoImpl(private val userDataSource: DataSource) : CustomerDao {
    override fun findByName(name: String): Customer? = statement("select * from CUSTOMERS where name = ?;") {
        setString(1, name)
        val rs = executeQuery()
        if (rs.next()) Customer(this@CustomerDaoImpl, rs.getString("name"), Money.of(CurrencyUnit.USD, rs.getBigDecimal("money")))
        else null
    }

    override fun update(customer: Customer) = statement<Unit>("update CUSTOMERS set money = ? where name = ?;") {
        setBigDecimal(1, customer.money.amount)
        setString(2, customer.name)
        executeUpdate()
    }

    override fun save(customer: Customer) = statement<Unit>("insert into CUSTOMERS (name, money) values (?, ?)") {
        setString(1, customer.name)
        setBigDecimal(2, customer.money.amount)
        executeUpdate()
    }

    override fun addProduct(product: Product, customer: Customer) = statement<Unit>("insert into PURCHASES (product_name, customer_name) values (?,?)") {
        setString(1, product.name)
        setString(2, customer.name)
        executeUpdate()
    }

    override fun deleteProduct(product: Product, customer: Customer) = statement<Unit>("delete from PURCHASES where product_name = ? and customer_name = ?") {
        setString(1, product.name)
        setString(2, customer.name)
        executeUpdate()
    }

    private fun <R> statement(sql: String, block: PreparedStatement.() -> R): R = userDataSource.connection.use { it.prepareStatement(sql).use(block) }
}