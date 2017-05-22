package com.jetradarmobile.sociallogin.facebook

import android.app.Activity
import android.content.Intent
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialLoginError
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import java.lang.ref.WeakReference


class FacebookNetwork(val permissions: List<String>) : SocialNetwork, FacebookCallback<LoginResult> {

    private var loginCallback: WeakReference<SocialLoginCallback>? = null
    private val callbackManager = CallbackManager.Factory.create()

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        this.loginCallback = WeakReference(callback)

        LoginManager.getInstance().registerCallback(callbackManager, this)

        val token = AccessToken.getCurrentAccessToken()
        val profile = Profile.getCurrentProfile()

        if (token == null) {
            LoginManager.getInstance().logInWithReadPermissions(activity, permissions)
        } else {
            val socialToken = createSocialToken(token, profile)
            loginCallback?.get()?.onLoginSuccess(this, socialToken)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun logout() {
        LoginManager.getInstance().logOut()
    }

    override fun onCancel() {
        loginCallback?.get()?.onLoginError(this, FacebookLoginError(SocialLoginError.Reason.CANCEL))
    }

    override fun onSuccess(result: LoginResult?) {
        val profile = Profile.getCurrentProfile()
        val token = result?.accessToken

        if (token != null) {
            val socialToken = createSocialToken(token, profile)
            loginCallback?.get()?.onLoginSuccess(this, socialToken)
        } else {
            loginCallback?.get()?.onLoginError(this, FacebookLoginError(FacebookLoginError.NO_LOGIN))
        }
    }

    override fun onError(error: FacebookException?) {
        loginCallback?.get()?.onLoginError(this,
                FacebookLoginError(error?.localizedMessage ?: "Facebook login error"))
    }

    private fun createSocialToken(accessToken: AccessToken, profile: Profile?) = SocialToken(
            token = accessToken.token,
            userId = accessToken.userId,
            userName = profile?.name ?: ""
    )

}