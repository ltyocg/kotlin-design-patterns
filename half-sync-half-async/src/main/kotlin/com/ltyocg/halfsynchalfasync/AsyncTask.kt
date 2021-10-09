package com.ltyocg.halfsynchalfasync

import java.util.concurrent.Callable

interface AsyncTask<O> : Callable<O> {
    fun onPreCall()
    fun onPostCall(result: O)
    fun onError(throwable: Throwable)
}