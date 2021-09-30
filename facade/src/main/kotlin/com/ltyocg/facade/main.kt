package com.ltyocg.facade

fun main() {
    with(DwarvenGoldmineFacade()) {
        startNewDay()
        digOutGold()
        endDay()
    }
}