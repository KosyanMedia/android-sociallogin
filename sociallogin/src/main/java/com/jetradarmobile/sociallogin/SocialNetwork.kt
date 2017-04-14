package com.jetradarmobile.sociallogin

import android.content.Intent

interface SocialNetwork {

    fun login(socialLoginFragment: SocialLoginFragment, callback: SocialLoginCallback)

    fun logout()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}