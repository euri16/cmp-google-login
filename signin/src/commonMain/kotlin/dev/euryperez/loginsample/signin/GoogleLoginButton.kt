package dev.euryperez.loginsample.signin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import dev.euryperez.loginsample.signin.models.AuthResponse
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.GoogleAuthProvider
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal expect fun GoogleLoginButton(
    onResponse: (AuthResponse) -> Unit,
    modifier: Modifier = Modifier
)

@Composable
fun GoogleButton(
    onResponse: (AuthResponse) -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    GoogleLoginButton(
        modifier = modifier,
        onResponse = { response ->
            coroutineScope.launch(Dispatchers.IO) {
                response.doOnSuccess { account ->
                    Firebase.auth.signInWithCredential(
                        GoogleAuthProvider.credential(account.idToken, account.accessToken)
                    )
                }

                withContext(Dispatchers.Main) { onResponse(response) }
            }
        }
    )
}
