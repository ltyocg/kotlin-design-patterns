interface AsyncTask<O> : () -> O {
    fun onPreCall()
    fun onPostCall(result: O)
    fun onError(throwable: Throwable)
}