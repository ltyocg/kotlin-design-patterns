package com.ltyocg.hexagonal

import com.ltyocg.hexagonal.domain.LotteryAdministration
import org.slf4j.Logger

interface ConsoleAdministrationSrv {
    fun getAllSubmittedTickets()
    fun performLottery()
    fun resetLottery()
}

class ConsoleAdministrationSrvImpl(
    private val administration: LotteryAdministration,
    private val logger: Logger
) : ConsoleAdministrationSrv {
    override fun getAllSubmittedTickets() {
        administration.allSubmittedTickets.forEach { (k, v) -> logger.info("Key: {}, Value: {}", k, v) }
    }

    override fun performLottery() {
        logger.info("The winning numbers: {}", administration.performLottery().numbersAsString)
        logger.info("Time to reset the database for next round, eh?")
    }

    override fun resetLottery() {
        administration.resetLottery()
        logger.info("The lottery ticket database was cleared.")
    }
}