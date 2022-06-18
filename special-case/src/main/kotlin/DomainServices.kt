object DomainServices {
    fun purchase(userName: String?, itemName: String?): ReceiptViewModel {
        val user = Db.findUserByUserName(userName) ?: return InvalidUser(userName)
        return purchase(user, Db.findAccountByUser(user)!!, itemName)
    }

    private fun purchase(user: Db.User, account: Db.Account, itemName: String?): ReceiptViewModel {
        val item = Db.findProductByItemName(itemName) ?: return OutOfStock(user.userName, itemName)
        val receipt = user.purchase(item)
        account.withdraw(receipt.price) ?: return InsufficientFunds(user.userName, account.amount, itemName)
        return receipt
    }
}