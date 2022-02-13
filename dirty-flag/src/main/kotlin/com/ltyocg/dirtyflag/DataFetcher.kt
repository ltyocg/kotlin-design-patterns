package com.ltyocg.dirtyflag

import org.slf4j.LoggerFactory
import java.io.File
import java.net.URLDecoder
import java.nio.charset.Charset


class DataFetcher {
    private val log = LoggerFactory.getLogger(javaClass)
    private val filename = "world.txt"
    private var lastFetched = -1L

    fun isDirty(fileLastModified: Long): Boolean =
        (lastFetched != fileLastModified).also { if (it) lastFetched = fileLastModified }

    fun fetch(): List<String> {
        val file = File(URLDecoder.decode(javaClass.classLoader.getResource(filename)!!.file, Charset.defaultCharset()))
        return if (isDirty(file.lastModified())) {
            log.info("{} is dirty! Re-fetching file content...", filename)
            file.readLines()
        } else emptyList()
    }
}