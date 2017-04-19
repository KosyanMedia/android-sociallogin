package com.jetradarmobile.sociallogin_odnoklassniki

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import org.json.JSONObject
import ru.ok.android.sdk.Odnoklassniki
import ru.ok.android.sdk.OkListener
import ru.ok.android.sdk.util.OkAuthType
import ru.ok.android.sdk.util.OkScope


class OdnoklassnikiNetwork : SocialNetwork, OkListener {

    private var loginCallback: SocialLoginCallback? = null

    private val APP_ID = "sociallogin__ok_app_id"
    private val PUBLIC_KEY = "sociallogin__ok_app_public_key"
    private val REDIRECT_URL = "sociallogin__ok_redirect_url"

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = callback

        val appId = getStringResByName(activity, APP_ID)
        val appKey = getStringResByName(activity, PUBLIC_KEY)
        val redirectUrl = getStringResByName(activity, REDIRECT_URL)

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

    private fun getStringResByName(ctx: Context, name: String): String {
        val resId = ctx.resources.getIdentifier(name, "string", ctx.getPackageName())
        try {
            return ctx.resources.getString(resId)
        } catch (e: Exception) {
            return ""
        }
    }
}