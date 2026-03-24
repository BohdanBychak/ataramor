package com.bbm.ataramor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform