package com.dev.mylife.earthsense

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.dev.mylife.earthsense.screen.EarthquakeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val androidNotifier = object : PlatformNotifier {
                override fun showToast(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            }
            CompositionLocalProvider(LocalNotifier provides androidNotifier) {
//                App()
                MaterialTheme {
                    EarthquakeScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()
    MaterialTheme {
        EarthquakeScreen()
    }
}