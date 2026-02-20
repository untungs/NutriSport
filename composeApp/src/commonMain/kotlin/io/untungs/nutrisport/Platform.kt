package io.untungs.nutrisport

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform