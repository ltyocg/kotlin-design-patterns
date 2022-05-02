import kotlin.reflect.KClass

enum class Role(private val typeCst: KClass<out CustomerRole>) {
    Borrower(BorrowerRole::class), Investor(InvestorRole::class);

    fun instance(): CustomerRole? = typeCst.java.getDeclaredConstructor().newInstance()
}