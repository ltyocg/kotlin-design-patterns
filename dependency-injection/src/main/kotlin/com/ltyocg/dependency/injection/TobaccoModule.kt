package com.ltyocg.dependency.injection

import com.google.inject.AbstractModule

class TobaccoModule : AbstractModule() {
    override fun configure() {
        bind(Tobacco::class.java).to(RivendellTobacco::class.java)
    }
}