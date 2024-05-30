object GatewayFactory {
    private val gateways = mutableMapOf<String, Gateway>()
    fun registerGateway(key: String, gateway: Gateway) {
        gateways[key] = gateway
    }

    fun getGateway(key: String): Gateway? = gateways[key]
}
