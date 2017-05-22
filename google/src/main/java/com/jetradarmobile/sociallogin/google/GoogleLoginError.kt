package com.jetradarmobile.sociallogin.google

import com.jetradarmobile.sociallogin.SocialLoginError


class GoogleLoginError(reason: Reason) : SocialLoginError(reason) {
    constructor(message: String) : this(Reason(message))

    object NO_LOGIN : Reason("Google account receive error")
}