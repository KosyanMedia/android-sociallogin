package com.jetradarmobile.sociallogin_facebook

import com.jetradarmobile.sociallogin.SocialLoginError


class FacebookLoginError(reason: Reason) : SocialLoginError(reason) {
    constructor(message: String) : this(Reason(message))

    object NO_LOGIN : Reason("No facebook login token present")
}