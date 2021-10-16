package com.ltyocg.hexagonal

import com.google.inject.Guice
import com.ltyocg.hexagonal.domain.LotteryAdministration
import com.ltyocg.hexagonal.domain.LotteryService

fun main() {
    val injector = Guice.createInjector(LotteryTestingModule())
    val administration = injector.getInstance(LotteryAdministration::class.java)
    administration.resetLottery()
    SampleData.submitTickets(injector.getInstance(LotteryService::class.java), 20)
    administration.performLottery()
}