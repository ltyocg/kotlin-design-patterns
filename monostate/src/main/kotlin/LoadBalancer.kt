class LoadBalancer {
    fun addServer(server: Server) {
        synchronized(servers) { servers.add(server) }
    }

    val noOfServers: Int
        get() = servers.size
    val lastServedId: Int
        get() = LoadBalancer.lastServedId

    @Synchronized
    fun serverRequest(request: Request) {
        if (LoadBalancer.lastServedId >= servers.size) LoadBalancer.lastServedId = 0
        servers[LoadBalancer.lastServedId++].serve(request)
    }

    private companion object {
        private val servers = intArrayOf(8080, 8081, 8082, 8083, 8084).mapIndexed { index, port ->
            Server("localhost", port, index + 1)
        }.toMutableList()
        private var lastServedId = 0
    }
}