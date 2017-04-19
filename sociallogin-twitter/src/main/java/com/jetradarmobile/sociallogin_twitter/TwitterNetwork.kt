package com.jetradarmobile.sociallogin_twitter

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.identity.TwitterAuthClient
import io.fabric.sdk.android.Fabric
import java.lang.ref.WeakReference

class TwitterNetwork : Callback<TwitterSession>(), SocialNetwork {

    private lateinit var authClient: TwitterAuthClient
    private var loginCallback: WeakReference<SocialLoginCallback>? = null

    private val APP_ID = "sociallogin__twitter_app_id"
    private val APP_SECRET = "sociallogin__twitter_app_secret"

    override fun login(activity: Activity, callback: SocialLoginCallback) {

        val appId = getStringResByName(activity, APP_ID)
        val appSecret = getStringResByName(activity, APP_SECRET)

        val authConfig = TwitterAuthConfig(appId, appSecret)

        Fabric.with(activity.applicationContext, TwitterCore(authConfig))

        authClient = TwitterAuthClient()
        loginCallback = WeakReference(callback)
        authClient.authorize(activity, this)
    }

    private fun getStringResByName(ctx: Context, name: String): String {
        val resId = ctx.resources.getIdentifier(name, "string", ctx.getPackageName())
        try {
            return ctx.resources.getString(resId)
        } catch (e: Exception) {
            return ""
        }
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
        loginCallback?.get()?.onLoginSuccess(this, createSocialToken(result?.data))
    }

    override fun failure(exception: TwitterException?) {
        loginCallback?.get()?.onLoginError(this, exception?.message ?: "Twitter authorization error")
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