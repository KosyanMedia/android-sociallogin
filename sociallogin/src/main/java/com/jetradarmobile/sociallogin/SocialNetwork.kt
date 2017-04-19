package com.jetradarmobile.sociallogin

import android.app.Activity
import android.content.Intent

interface SocialNetwork {

    fun login(activity: Activity, callback: SocialLoginCallback)

    fun logout()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}