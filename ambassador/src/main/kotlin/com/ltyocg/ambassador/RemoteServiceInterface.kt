package com.ltyocg.ambassador

interface RemoteServiceInterface {
    suspend fun doRemoteFunction(value: Int): Long
}