package com.jetradarmobile.sociallogin

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class SocialLoginFragment : Fragment() {
    companion object {
        const val TAG = "SocialLogin__SocialLoginFragment"

        @JvmStatic fun from(fragmentManager: FragmentManager): SocialLoginFragment {
            var fragment = fragmentManager.findFragmentByTag(TAG) as SocialLoginFragment?
            if (fragment == null) {
                fragment = SocialLoginFragment()
                with(fragmentManager){
                    beginTransaction().add(fragment, TAG).commit()
                    executePendingTransactions()
                }
            }
            return fragment
        }
    }

    private lateinit var socialNetwork: SocialNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        socialNetwork.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun requestLogin(socialNetwork: SocialNetwork, socialLoginCallback: SocialLoginCallback){
        this.socialNetwork = socialNetwork
        socialNetwork.login(this, socialLoginCallback)
    }
}

