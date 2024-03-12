package dev.euryperez.loginsample

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform