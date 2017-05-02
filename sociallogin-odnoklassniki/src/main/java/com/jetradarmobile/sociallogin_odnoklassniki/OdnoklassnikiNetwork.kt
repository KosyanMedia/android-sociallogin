package com.jetradarmobile.sociallogin_odnoklassniki

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import org.json.JSONObject
import ru.ok.android.sdk.Odnoklassniki
import ru.ok.android.sdk.OkListener
import ru.ok.android.sdk.util.OkAuthType
import ru.ok.android.sdk.util.OkScope


class OdnoklassnikiNetwork(
        val appId: String,
        val appKey: String,
        val redirectUrl: String) : SocialNetwork, OkListener {

    private var loginCallback: SocialLoginCallback? = null

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = callback

        val okInstance = Odnoklassniki.createInstance(activity, appId, appKey)

        okInstance.requestAuthorization(
                activity,
                redirectUrl,
                OkAuthType.ANY,
                OkScope.VALUABLE_ACCESS,
                OkScope.LONG_ACCESS_TOKEN)
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
        loginCallback?.onLoginError(this, error?: "Odnoklassniki login error")
    }

    private fun createSocialToken(json: JSONObject?): SocialToken {
        return SocialToken(
                token = json?.getString("access_token") ?: "",
                userId = "",
                userName = "",
                email = ""
        )
    }
}