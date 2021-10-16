package com.ltyocg.hexagonal

import java.io.FileInputStream
import java.util.*

object MongoConnectionPropertiesLoader {
    private const val DEFAULT_HOST = "localhost"
    private const val DEFAULT_PORT = 27017
    fun load() {
        var host = DEFAULT_HOST
        var port = DEFAULT_PORT
        val path = System.getProperty("hexagonal.properties.path")
        val properties = Properties()
        if (path != null) {
            FileInputStream(path).use {
                properties.load(it)
                host = properties.getProperty("mongo-host")
                port = properties.getProperty("mongo-port").toInt()
            }
        }
        System.setProperty("mongo-host", host)
        System.setProperty("mongo-port", port.toString())
    }
}