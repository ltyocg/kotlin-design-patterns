object Db {
    private val userName2User = mutableMapOf<String, User>()
    private val user2Account = mutableMapOf<User, Account>()
    private val itemName2Product = mutableMapOf<String, Product>()
    fun seedUser(userName: String, amount: Double) {
        val user = User(userName)
        userName2User[userName] = user
        user2Account[user] = Account(amount)
    }

    fun seedItem(itemName: String, price: Double) {
        itemName2Product[itemName] = Product(price)
    }

    fun findUserByUserName(userName: String?): User? = userName2User[userName]
    fun findAccountByUser(user: User): Account? = user2Account[user]
    fun findProductByItemName(itemName: String?): Product? = itemName2Product[itemName]
    class User(val userName: String) {
        fun purchase(item: Product): ReceiptDto = ReceiptDto(item.price)
    }

    class Account(val amount: Double) {
        fun withdraw(price: Double): MoneyTransaction? = if (price > amount) null else MoneyTransaction(amount, price)
    }

    class Product(val price: Double)
    class MoneyTransaction(private val amount: Double, private val price: Double)
}