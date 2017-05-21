package com.jetradarmobile.sociallogin.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jetradarmobile.sociallogin.SocialToken
import com.jetradarmobile.sociallogin_facebook.FacebookNetwork
import com.jetradarmobile.sociallogin_google.GoogleNetwork
import com.jetradarmobile.sociallogin_odnoklassniki.OkNetwork
import com.jetradarmobile.sociallogin_rx.RxSocialLogin
import com.jetradarmobile.sociallogin_twitter.TwitterNetwork
import com.jetradarmobile.sociallogin_vkontakte.VkNetwork
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import ru.ok.android.sdk.util.OkScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        facebookButton.setOnClickListener {
            val scope = mutableListOf<String>()
            scope.add("public_profile")
            scope.add("email")

            subscribe(RxSocialLogin.instance.loginTo(this, FacebookNetwork(scope)))
        }

        vkontakteButton.setOnClickListener {
            val scope = mutableListOf<String>()
            scope.add("access_token")
            scope.add("email")

            subscribe(RxSocialLogin.instance.loginTo(this, VkNetwork(scope)))
        }

        twitterButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, TwitterNetwork(
                    getString(R.string.twitter_app_id),
                    getString(R.string.twitter_app_secret))))
        }

        odnoklassnikiButton.setOnClickListener {
            val scope = mutableListOf<String>()
            scope.add(OkScope.VALUABLE_ACCESS)
            scope.add(OkScope.LONG_ACCESS_TOKEN)

            subscribe(RxSocialLogin.instance.loginTo(this, OkNetwork(
                    getString(R.string.ok_app_id),
                    getString(R.string.ok_app_public_key),
                    getString(R.string.ok_redirect_url),
                    scope)))
        }

        googleButton.setOnClickListener {
            subscribe(RxSocialLogin.instance.loginTo(this, GoogleNetwork(
                    getString(R.string.google_server_client_id)
            )))
        }
    }

    private fun subscribe(observable: Observable<SocialToken>) {
        observable.subscribe(
                { token -> showText(getTokenText(token)) },
                { error -> showText(error.message ?: "error") })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        RxSocialLogin.instance.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showText(text: String) {
        info.text = text
    }

    private fun getTokenText(token: SocialToken) = "token = ${token.token} \n\n" +
            "user id = ${token.userId} \n\n" +
            "user name = ${token.userName} \n\n" +
            "email = ${token.email} \n\n"
}
