package dev.euryperez.loginsample.signin.models

data class GoogleAccount(
    val idToken: String,
    val accessToken: String,
    val profile: Profile
)

data class Profile(
    val name: String,
    val familyName: String,
    val givenName: String,
    val email: String,
    val picture: String?
)
