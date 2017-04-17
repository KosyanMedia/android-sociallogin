package com.jetradarmobile.sociallogin

import android.support.v4.app.FragmentActivity


class SocialLogin private constructor(activity: FragmentActivity) {

    private val socialLoginFragment = SocialLoginFragment.from(activity.fragmentManager)

    companion object Factory {
        fun with(activity: FragmentActivity): SocialLogin {
            return SocialLogin(activity)
        }
    }

    fun loginTo(socialNetwork: SocialNetwork, socialLoginCallback: SocialLoginCallback): SocialLogin {
        socialLoginFragment.requestLogin(socialNetwork, socialLoginCallback)
        return this
    }
}
