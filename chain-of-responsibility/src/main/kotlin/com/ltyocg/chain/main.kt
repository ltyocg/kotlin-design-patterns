package com.ltyocg.chain

fun main() {
    with(OrcKing()) {
        makeRequest(Request(RequestType.DEFEND_CASTLE, "defend castle"))
        makeRequest(Request(RequestType.TORTURE_PRISONER, "torture prisoner"))
        makeRequest(Request(RequestType.COLLECT_TAX, "collect tax"))
    }
}