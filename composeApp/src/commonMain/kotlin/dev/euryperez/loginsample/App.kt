package dev.euryperez.loginsample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.euryperez.loginsample.utils.compositionlocals.LocalResources
import dev.euryperez.loginsample.signin.GoogleButton
import dev.euryperez.loginsample.signin.models.AuthResponse
import dev.euryperez.loginsample.utils.ResourcesImpl
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    CompositionLocalProvider(LocalResources provides ResourcesImpl()) {
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxSize().background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GoogleButton(
                    onResponse = {
                        println((it as? AuthResponse.Success)?.account?.profile?.email)
                    }
                )
            }
        }
    }
}
