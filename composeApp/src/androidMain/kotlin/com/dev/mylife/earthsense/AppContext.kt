package com.dev.mylife.earthsense
import android.content.Context
import java.lang.ref.WeakReference

object AppContext {
    private var contextRef: WeakReference<Context>? = null

    fun set(context: Context) {
        contextRef = WeakReference(context.applicationContext)
    }

    fun get(): Context {
        return contextRef?.get() ?: throw IllegalStateException("AppContext not initialized")
    }
}