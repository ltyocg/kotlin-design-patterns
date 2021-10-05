package com.ltyocg.featuretoggle

import org.slf4j.LoggerFactory
import java.util.*

private val log = LoggerFactory.getLogger("main")

fun main() {
    log.info(PropertiesFeatureToggleVersion(Properties().apply {
        put("enhancedWelcome", true)
    }).getWelcomeMessage(User("Jamie No Code")))
    log.info(PropertiesFeatureToggleVersion(Properties().apply {
        put("enhancedWelcome", false)
    }).getWelcomeMessage(User("Jamie No Code")))
    val service = TieredFeatureToggleVersion()
    val paidUser = User("Jamie Coder")
    val freeUser = User("Alan Defect")
    UserGroup.addUserToPaidGroup(paidUser)
    UserGroup.addUserToFreeGroup(freeUser)
    log.info(service.getWelcomeMessage(paidUser))
    log.info(service.getWelcomeMessage(freeUser))
}