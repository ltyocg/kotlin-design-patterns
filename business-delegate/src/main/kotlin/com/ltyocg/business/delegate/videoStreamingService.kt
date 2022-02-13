package com.ltyocg.business.delegate

import org.slf4j.LoggerFactory

interface VideoStreamingService {
    fun doProcessing()
}

class NetflixService : VideoStreamingService {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun doProcessing() {
        log.info("NetflixService is now processing")
    }
}

class YouTubeService : VideoStreamingService {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun doProcessing() {
        log.info("YouTubeService is now processing")
    }
}
