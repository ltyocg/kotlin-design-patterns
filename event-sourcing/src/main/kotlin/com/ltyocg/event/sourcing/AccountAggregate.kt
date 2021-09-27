package com.ltyocg.event.sourcing

object AccountAggregate {
    private var accounts = mutableMapOf<Int, Account>()
    fun putAccount(account: Account) {
        accounts[account.accountNo] = account
    }

    fun getAccount(accountNo: Int): Account? = accounts[accountNo]?.copy()
    fun resetState() {
        accounts = mutableMapOf()
    }
}