package com.jetradarmobile.sociallogin_vkontakte

import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialLoginFragment
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import java.lang.ref.WeakReference


class VkontakteNetwork : SocialNetwork, VKCallback<VKAccessToken> {

    private var loginCallback: WeakReference<SocialLoginCallback>? = null

    override fun login(socialLoginFragment: SocialLoginFragment, callback: SocialLoginCallback) {
        loginCallback = WeakReference(callback)
        VKSdk.login(socialLoginFragment, "access_token")
    }

    override fun logout() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        VKSdk.onActivityResult(requestCode, resultCode, data, this)
    }

    override fun onResult(token: VKAccessToken?) {
        loginCallback?.get()?.let {
            if (token != null) {
                it.onLoginSuccess(this, createSocialToken(token))
            } else {
                it.onLoginError(this, "Vkontakte token receiving error")
            }
        }
    }

    override fun onError(error: VKError?) {
        loginCallback?.get()?.onLoginError(this, error?.errorMessage ?: "Vkontakte authorization error")
    }

    private fun createSocialToken(vkAccessToken: VKAccessToken?): SocialToken {
        return SocialToken(
                token = vkAccessToken?.accessToken ?: "",
                userId = vkAccessToken?.userId ?: "",
                userName = ""
        )
    }
}