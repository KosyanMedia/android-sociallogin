package com.jetradarmobile.sociallogin_rx

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import io.reactivex.Observable


class RxSocialLogin {

    private lateinit var socialNetwork: SocialNetwork

    private object Holder {
        val INSTANCE = RxSocialLogin()
    }

    companion object Factory {
        val instance: RxSocialLogin by lazy { Holder.INSTANCE }
    }

    fun loginTo(activity: Activity, socialNetwork: SocialNetwork): Observable<SocialToken> {
        this.socialNetwork = socialNetwork
        return Observable.create { subscriber ->

            val callback = object : SocialLoginCallback {
                override fun onLoginSuccess(socialNetwork: SocialNetwork, token: SocialToken) {
                    subscriber.onNext(token)
                }

                override fun onLoginError(socialNetwork: SocialNetwork, errorMessage: String) {
                    subscriber.onError(Throwable(errorMessage))
                }
            }

            this.socialNetwork.login(activity, callback)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialNetwork.onActivityResult(requestCode, resultCode, data)
    }
}