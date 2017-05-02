package com.jetradarmobile.sociallogin_twitter

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import io.fabric.sdk.android.Fabric

class TwitterNetwork(
    val appId: String,
    val appSecret: String) : Callback<TwitterSession>(), SocialNetwork {

    private lateinit var authClient: TwitterAuthClient
    private var loginCallback: SocialLoginCallback? = null

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = callback

        val authConfig = TwitterAuthConfig(appId, appSecret)

        Fabric.with(activity.applicationContext, TwitterCore(authConfig))

        authClient = TwitterAuthClient()
        authClient.authorize(activity, this)
    }

    override fun logout() {
        val twitterSession = TwitterCore.getInstance().sessionManager.activeSession
        if (twitterSession != null) {
            TwitterCore.getInstance().sessionManager.clearActiveSession()
            TwitterCore.getInstance().logOut()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        authClient.onActivityResult(requestCode, resultCode, data)
    }

    override fun success(result: Result<TwitterSession>?) {
        loginCallback?.onLoginSuccess(this, createSocialToken(result?.data))
    }

    override fun failure(exception: TwitterException?) {
        loginCallback?.onLoginError(this, exception?.message ?: "Twitter authorization error")
    }

    private fun createSocialToken(session: TwitterSession?): SocialToken {
        return SocialToken(
                token = session?.authToken?.token ?: "",
                userId = session?.userId?.toString() ?: "",
                userName = session?.userName?: "",
                email = ""
        )
    }
}