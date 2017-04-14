package com.jetradarmobile.sociallogin

/**
 *  Callback interface. Handle login result
 */
interface SocialLoginCallback {

    /**
     * Calls when login was successful
     *
     * @param socialNetwork [SocialNetwork] implementation in which login was requested
     * @param socialToken [SocialToken] authorization token and some user data
     */
    fun onLoginSuccess(socialNetwork: SocialNetwork, socialToken: SocialToken)

    /**
     * Calls when some error occurred
     *
     * @param socialNetwork [SocialNetwork] implementation with which request was unsuccessful
     * @param errorMessage [String] errorMessage message
     */
    fun onLoginError(socialNetwork: SocialNetwork, errorMessage: String)
}