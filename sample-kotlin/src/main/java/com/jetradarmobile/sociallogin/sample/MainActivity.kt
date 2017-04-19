package com.jetradarmobile.sociallogin.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jetradarmobile.sociallogin.SocialToken
import com.jetradarmobile.sociallogin_facebook.FacebookNetwork
import com.jetradarmobile.sociallogin_google.GoogleNetwork
import com.jetradarmobile.sociallogin_odnoklassniki.OdnoklassnikiNetwork
import com.jetradarmobile.sociallogin_rx.RxSocialLogin
import com.jetradarmobile.sociallogin_twitter.TwitterNetwork
import com.jetradarmobile.sociallogin_vkontakte.VkontakteNetwork
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        facebookButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, FacebookNetwork()))
        }

        vkontakteButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, VkontakteNetwork()))
        }

        twitterButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, TwitterNetwork()))
        }

        odnoklassnikiButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, OdnoklassnikiNetwork()))
        }

        googleButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, GoogleNetwork()))
        }
    }

    private fun subscribe(observable: Observable<SocialToken>) {
        observable.doOnNext { token -> showText(getTokenText(token)) }
                .doOnError { error -> showText(error.message ?: "error") }
                .subscribe()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        RxSocialLogin.instance.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showText(text: String) {
        info.text = text
    }

    private fun getTokenText(token: SocialToken): String {
        return "token = ${token.token} \n\n" +
                "user id = ${token.userId} \n\n" +
                "user name = ${token.userName} \n\n" +
                "email = ${token.email} \n\n"
    }
}
