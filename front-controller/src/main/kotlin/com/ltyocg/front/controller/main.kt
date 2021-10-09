package com.ltyocg.front.controller

fun main() {
    with(FrontController()) {
        handleRequest("Archer")
        handleRequest("Catapult")
        handleRequest("foobar")
    }
}