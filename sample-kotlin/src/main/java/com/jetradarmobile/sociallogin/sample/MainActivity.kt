package com.jetradarmobile.sociallogin.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jetradarmobile.sociallogin.SocialLogin
import com.jetradarmobile.sociallogin.SocialLoginCallback
import com.jetradarmobile.sociallogin.SocialNetwork
import com.jetradarmobile.sociallogin.SocialToken
import com.jetradarmobile.sociallogin_facebook.FacebookNetwork
import com.jetradarmobile.sociallogin_google.GoogleNetwork
import com.jetradarmobile.sociallogin_odnoklassniki.OdnoklassnikiNetwork
import com.jetradarmobile.sociallogin_twitter.TwitterNetwork
import com.jetradarmobile.sociallogin_vkontakte.VkontakteNetwork
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SocialLoginCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        facebookButton.setOnClickListener {
            SocialLogin.instance.loginTo(this, FacebookNetwork(), this)
        }

        vkontakteButton.setOnClickListener {
            SocialLogin.instance.loginTo(this, VkontakteNetwork(), this)
        }

        twitterButton.setOnClickListener {
            SocialLogin.instance.loginTo(this, TwitterNetwork(), this)
        }

        odnoklassnikiButton.setOnClickListener {
            SocialLogin.instance.loginTo(this, OdnoklassnikiNetwork(), this)
        }

        googleButton.setOnClickListener {
            SocialLogin.instance.loginTo(this, GoogleNetwork(), this)
        }
    }

    override fun onLoginSuccess(socialNetwork: SocialNetwork, token: SocialToken) {
        info.text = "token = ${token.token} \n\n" +
                "user id = ${token.userId} \n\n" +
                "user name = ${token.userName} \n\n" +
                "email = ${token.email} \n\n"

    }

    override fun onLoginError(socialNetwork: SocialNetwork, errorMessage: String) {
        info.text = errorMessage
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        SocialLogin.instance.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
