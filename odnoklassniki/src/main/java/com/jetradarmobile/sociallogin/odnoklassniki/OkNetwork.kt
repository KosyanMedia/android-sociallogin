package com.jetradarmobile.sociallogin.odnoklassniki

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialLoginError
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import org.json.JSONObject
import ru.ok.android.sdk.Odnoklassniki
import ru.ok.android.sdk.OkListener
import ru.ok.android.sdk.util.OkAuthType


class OkNetwork(
        val appId: String,
        val appKey: String,
        val redirectUrl: String,
        val scope: List<String>) : SocialNetwork, OkListener {

    private var loginCallback: SocialLoginCallback? = null

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = callback

        val okInstance = Odnoklassniki.createInstance(activity, appId, appKey)

        okInstance.requestAuthorization(
                activity,
                redirectUrl,
                OkAuthType.ANY,
                *scope.toTypedArray())
    }

    override fun logout() {
        Odnoklassniki.getInstance().clearTokens()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Odnoklassniki.getInstance().onAuthActivityResult(requestCode, resultCode, data, this)
    }

    override fun onSuccess(json: JSONObject?) {
        loginCallback?.onLoginSuccess(this, createSocialToken(json))
    }

    override fun onError(error: String?) {
        loginCallback?.onLoginError(this, SocialLoginError(error ?: "Odnoklassniki login error"))
    }

    private fun createSocialToken(json: JSONObject?)
            = SocialToken(token = json?.getString("access_token") ?: "")

}