package com.ltyocg.business.delegate

fun main() {
    val client = MobileClient(BusinessDelegate(BusinessLookup(NetflixService(), YouTubeService())))
    client.playbackMovie("Die Hard 2")
    client.playbackMovie("Maradona: The Greatest Ever")
}