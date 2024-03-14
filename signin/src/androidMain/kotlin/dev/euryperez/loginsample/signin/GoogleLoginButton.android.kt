package dev.euryperez.loginsample.signin

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dev.euryperez.loginsample.signin.models.AuthResponse
import dev.euryperez.loginsample.signin.models.GoogleAccount
import dev.euryperez.loginsample.signin.models.Profile

@Composable
internal actual fun GoogleLoginButton(
    onResponse: (AuthResponse) -> Unit,
    modifier: Modifier
) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken("PLACE_YOUR_CLIENT_ID_HERE")
        .requestEmail()
        .build()

    val activity = LocalContext.current as Activity
    val googleSignInClient = GoogleSignIn.getClient(activity, gso)

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.getResult(ApiException::class.java)!!

                val googleAccount = GoogleAccount(
                    idToken = account.idToken.toString(),
                    accessToken = account.serverAuthCode.toString(),
                    profile = Profile(
                        name = account.displayName.orEmpty(),
                        familyName = account.familyName.orEmpty(),
                        givenName = account.givenName.orEmpty(),
                        email = account.email.orEmpty(),
                        picture = account.photoUrl?.toString().orEmpty()
                    ),
                )

                onResponse(AuthResponse.Success(googleAccount))
            } catch (e: ApiException) {
                if (result.resultCode == Activity.RESULT_CANCELED) {
                    AuthResponse.Cancelled
                } else {
                    AuthResponse.Error(e.fullErrorMessage)
                }.also(onResponse)

                Log.w("TAG", "Google sign in failed", e)
            }
        }

    GoogleButtonUI(
        modifier = modifier,
        onClick = { launcher.launch(googleSignInClient.signInIntent) },
    )
}

private val ApiException.fullErrorMessage: String
    get() {
        return listOfNotNull(
            "code: $statusCode",
            message?.let { "message: $message" },
            "localizedMessage: $localizedMessage",
            "status: $status",
        ).joinToString("\n")
    }
