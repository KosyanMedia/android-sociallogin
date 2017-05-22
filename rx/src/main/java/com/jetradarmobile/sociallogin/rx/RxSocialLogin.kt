package com.jetradarmobile.sociallogin.rx

import android.app.Activity
import android.content.Intent
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialLoginError
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


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
        val observable: Observable<SocialToken> = Observable.create { subscriber ->

            val callback = object : SocialLoginCallback {

                override fun onLoginSuccess(socialNetwork: SocialNetwork, token: SocialToken) {
                    subscriber.onNext(token)
                    subscriber.onComplete()
                }

                override fun onLoginError(socialNetwork: SocialNetwork, error: SocialLoginError) {
                    subscriber.onError(error)
                }
            }

            this.socialNetwork.login(activity, callback)
        }

        return observable.observeOn(Schedulers.io())
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialNetwork.onActivityResult(requestCode, resultCode, data)
    }
}