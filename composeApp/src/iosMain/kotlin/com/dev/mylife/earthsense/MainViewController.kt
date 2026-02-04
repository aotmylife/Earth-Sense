package com.dev.mylife.earthsense

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.dev.mylife.earthsense.di.initKoin
import com.dev.mylife.earthsense.screen.EarthquakeScreen
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.NSEC_PER_SEC
import platform.darwin.dispatch_after
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time

private val koinApp = initKoin {}

fun MainViewController() = ComposeUIViewController {

    val iosNotifier = object : PlatformNotifier {
        override fun showToast(message: String) {
            val alert = UIAlertController.alertControllerWithTitle(
                title = null,
                message = message,
                preferredStyle = UIAlertControllerStyleAlert
            )

            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(alert, true, null)

            dispatch_after(dispatch_time(DISPATCH_TIME_NOW, 2L * NSEC_PER_SEC.toLong()), dispatch_get_main_queue()) {
                alert.dismissViewControllerAnimated(true, null)
            }
        }
    }

    CompositionLocalProvider(LocalNotifier provides iosNotifier) {
//        App()
        MaterialTheme {
            EarthquakeScreen()
        }
    }
}