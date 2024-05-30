fun main() {
    GatewayFactory.registerGateway("ServiceA", ExternalServiceA())
    GatewayFactory.registerGateway("ServiceB", ExternalServiceB())
    GatewayFactory.registerGateway("ServiceC", ExternalServiceC())
    GatewayFactory.getGateway("ServiceA")!!.execute()
    GatewayFactory.getGateway("ServiceB")!!.execute()
    GatewayFactory.getGateway("ServiceC")!!.execute()
}
