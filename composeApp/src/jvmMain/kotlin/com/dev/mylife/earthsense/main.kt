package com.dev.mylife.earthsense

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.dev.mylife.earthsense.screen.EarthquakeScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "EarthSense",
    ) {
//        App()
        MaterialTheme {
            EarthquakeScreen()
        }
    }
}