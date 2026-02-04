package com.dev.mylife.earthsense

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun showToast(message: String) {
    println("Toast on JVM: $message")
}