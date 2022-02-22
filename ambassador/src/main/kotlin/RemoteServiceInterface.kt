interface RemoteServiceInterface {
    suspend fun doRemoteFunction(value: Int): Long
}