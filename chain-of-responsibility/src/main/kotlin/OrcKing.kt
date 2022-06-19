object OrcKing {
    private val chain = OrcSoldier().let(::OrcOfficer).let(::OrcCommander)
    fun makeRequest(req: Request) = chain.handleRequest(req)
}