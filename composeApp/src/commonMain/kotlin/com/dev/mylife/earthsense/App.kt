package com.dev.mylife.earthsense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource

import earthsense.composeapp.generated.resources.Res
import earthsense.composeapp.generated.resources.compose_multiplatform
import org.koin.compose.viewmodel.koinViewModel

interface PlatformNotifier {
    fun showToast(message: String)
}

val LocalNotifier = staticCompositionLocalOf<PlatformNotifier> {
    error("No Notifier provided")
}

@Composable
@Preview
fun App(viewModel: MainViewModel = koinViewModel()) {

    val helloText by viewModel.helloText.collectAsState()

    val notifier = LocalNotifier.current

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
                viewModel.updateText("HELLO!! Shared Code From ANDROID")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                    Text("Message: $helloText")
                    notifier.showToast(helloText)
                }
            }
        }
    }
}