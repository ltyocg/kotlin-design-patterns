class OrcKing {
    private val chain = OrcCommander(OrcOfficer(OrcSoldier()))

    fun makeRequest(req: Request) {
        chain.handleRequest(req)
    }
}