package dev.euryperez.loginsample.utils.compositionlocals

import androidx.compose.runtime.compositionLocalOf
import dev.euryperez.loginsample.utils.Resources

val LocalResources = compositionLocalOf<Resources> {
    error("No LocalResources provided")
}
