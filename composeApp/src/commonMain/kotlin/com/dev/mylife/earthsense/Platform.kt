package com.dev.mylife.earthsense

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

// commonMain
expect fun showToast(message: String)