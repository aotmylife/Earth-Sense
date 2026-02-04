@file:Suppress("DEPRECATION")

package com.dev.mylife.earthsense

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport
import com.dev.mylife.earthsense.di.commonModule
import com.dev.mylife.earthsense.screen.EarthquakeScreen
import org.koin.compose.KoinContext
import org.koin.core.context.startKoin

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin { modules(commonModule) }

    ComposeViewport(viewportContainerId = "ComposeTarget") {
        KoinContext {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    EarthquakeScreen()
                }
            }
        }
    }
}