package com.jetradarmobile.sociallogin


open class SocialLoginError(val reason: Reason) : Throwable(reason.message) {
    constructor(message: String) : this(Reason(message))

    open class Reason(val message: String) {
        object CANCEL : Reason("Authorization process was cancelled")
    }
}