package dev.euryperez.loginsample.signin

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.LocalUIViewController
import cocoapods.GoogleSignIn.GIDSignIn
import dev.euryperez.loginsample.signin.models.AuthResponse
import dev.euryperez.loginsample.signin.models.GoogleAccount
import dev.euryperez.loginsample.signin.models.Profile
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError
import platform.UIKit.UIViewController

@Composable
internal actual fun GoogleLoginButton(
    onResponse: (AuthResponse) -> Unit,
    modifier: Modifier
) {
    val uiViewController = LocalUIViewController.current

    GoogleButtonUI(
        modifier = modifier,
        onClick = { googleLogin(uiViewController, onResponse) }
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun googleLogin(
    uiViewController: UIViewController,
    onLoggedIn: (AuthResponse) -> Unit
) {
    GIDSignIn.sharedInstance.signInWithPresentingViewController(uiViewController) { user, error ->
        if (user == null) {
            onLoggedIn(AuthResponse.Error(error?.fullErrorMessage ?: "Unknown error"))
            println("Error: $error")
            return@signInWithPresentingViewController
        }

        val googleAccount = GoogleAccount(
            idToken = user.user.idToken?.tokenString.orEmpty(),
            accessToken = user.user.accessToken.tokenString,
            profile = Profile(
                name = user.user.profile?.name.orEmpty(),
                familyName = user.user.profile?.familyName.orEmpty(),
                givenName = user.user.profile?.givenName.orEmpty(),
                email = user.user.profile?.email.orEmpty(),
                picture = user.user.profile?.imageURLWithDimension(100u)?.absoluteString
            ),
        )

        onLoggedIn(AuthResponse.Success(googleAccount))
    }
}

private val NSError.fullErrorMessage: String
    get() {
        val underlyingErrors = underlyingErrors.joinToString(", ") { it.toString() }
        val recoveryOptions = localizedRecoveryOptions?.joinToString(", ") { it.toString() }

        return listOfNotNull(
            "code: $code",
            domain?.let { "domain: $domain" },
            "description: $localizedDescription",
            localizedFailureReason?.let { "reason: $localizedFailureReason" },
            localizedRecoverySuggestion?.let { "suggestion: $localizedRecoverySuggestion" },
            "underlyingErrors: $underlyingErrors",
            "recoveryOptions: $recoveryOptions".takeIf { recoveryOptions != null },
        ).joinToString("\n")
    }
