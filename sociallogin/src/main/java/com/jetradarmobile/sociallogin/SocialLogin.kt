package com.jetradarmobile.sociallogin

import android.app.Activity
import android.content.Intent


class SocialLogin private constructor() {

    private lateinit var socialNetwork: SocialNetwork

    private object Holder {
        val INSTANCE = SocialLogin()
    }

    companion object Factory {
        val instance: SocialLogin by lazy { Holder.INSTANCE }
    }

    fun loginTo(activity: Activity, socialNetwork: SocialNetwork, callback: SocialLoginCallback): SocialLogin {
        this.socialNetwork = socialNetwork
        this.socialNetwork.login(activity, callback)
        return this
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialNetwork.onActivityResult(requestCode, resultCode, data)
    }
}
