package com.ltyocg.caching

enum class CachingPolicy(val policy: String) {
    THROUGH("through"),
    AROUND("around"),
    BEHIND("behind"),
    ASIDE("aside")
}