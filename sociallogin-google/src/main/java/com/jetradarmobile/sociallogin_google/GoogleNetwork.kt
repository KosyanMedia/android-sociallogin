package com.jetradarmobile.sociallogin_google

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.common.api.GoogleApiClient
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import java.lang.ref.WeakReference


class GoogleNetwork : SocialNetwork,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    private lateinit var googleApiClient: GoogleApiClient
    private var loginCallback: WeakReference<SocialLoginCallback>? = null

    private val REQUEST_CODE = 0x0C1E

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = WeakReference(callback)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        activity.startActivityForResult(signInIntent, REQUEST_CODE)
    }

    override fun logout() {
        Auth.GoogleSignInApi.signOut(googleApiClient)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        googleApiClient.disconnect()
        if (!result.isSuccess) {
            loginCallback?.get()?.onLoginError(this, "Google login error")
            return
        }

        val acct = result.signInAccount
        if (acct != null) {
            loginCallback?.get()?.onLoginSuccess(this, createSocialToken(acct))
        } else {
            loginCallback?.get()?.onLoginError(this, "Google account receive error")
        }
    }

    private fun createSocialToken(account: GoogleSignInAccount): SocialToken {
        return SocialToken(
                token = account.id ?: "",
                userId = "",
                userName = account.displayName ?: "",
                email = account.email ?: ""
        )
    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(cause: Int) {
        val error = CommonStatusCodes.getStatusCodeString(cause)
        loginCallback?.get()?.onLoginError(this, error)
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        loginCallback?.get()?.onLoginError(this, result.errorMessage ?: "Google login connection error")
    }
}