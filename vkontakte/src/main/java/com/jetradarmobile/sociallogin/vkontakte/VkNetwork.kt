package com.jetradarmobile.sociallogin.vkontakte

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialLoginError
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError


class VkNetwork(val scope: List<String>) : SocialNetwork, VKCallback<VKAccessToken> {

    private var loginCallback: SocialLoginCallback? = null

    override fun login(activity: Activity, callback: SocialLoginCallback) {
        loginCallback = callback
        VKSdk.login(activity, *scope.toTypedArray())
    }

    override fun logout() {
        VKSdk.logout()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, this)
    }

    override fun onResult(token: VKAccessToken?) {
        loginCallback?.let {
            if (token != null) {
                it.onLoginSuccess(this, createSocialToken(token))
            } else {
                it.onLoginError(this, VkLoginError(VkLoginError.NO_LOGIN))
            }
        }
    }

    override fun onError(error: VKError?) {
        loginCallback?.onLoginError(this,
                SocialLoginError(error?.errorMessage ?: "Vk authorization error"))
    }

    private fun createSocialToken(vkAccessToken: VKAccessToken?) = SocialToken(
            token = vkAccessToken?.accessToken ?: "",
            userId = vkAccessToken?.userId ?: "",
            email = vkAccessToken?.email ?: ""
    )
}