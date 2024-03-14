package dev.euryperez.loginsample.utils

import cmp_google_login.composeapp.generated.resources.Res
import cmp_google_login.composeapp.generated.resources.ic_google
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

class ResourcesImpl : Resources {
    override val drawables: Drawables = DrawablesImpl()
}

@OptIn(ExperimentalResourceApi::class)
private class DrawablesImpl : Drawables {
    override val googleIcon = Res.drawable.ic_google
}
