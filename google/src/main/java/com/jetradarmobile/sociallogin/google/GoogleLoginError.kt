package com.jetradarmobile.sociallogin.google


import com.jetradarmobile.sociallogin.SocialLoginError


class GoogleLoginError(reason: Reason) : SocialLoginError(reason) {
    constructor(message: String) : this(Reason(message))

    object NO_LOGIN : Reason("Google account receive error")
    object SERVICE_VERSION_UPDATE_REQUIRED : Reason("SERVICE_VERSION_UPDATE_REQUIRED")
    object SERVICE_DISABLED : Reason("SERVICE_DISABLED")
    object SIGN_IN_REQUIRED : Reason("SIGN_IN_REQUIRED")
    object INVALID_ACCOUNT : Reason("INVALID_ACCOUNT")
    object RESOLUTION_REQUIRED : Reason("RESOLUTION_REQUIRED")
    object NETWORK_ERROR : Reason("NETWORK_ERROR")
    object INTERNAL_ERROR : Reason("INTERNAL_ERROR")
    object DEVELOPER_ERROR : Reason("DEVELOPER_ERROR")
    object ERROR : Reason("ERROR")
    object INTERRUPTED : Reason("INTERRUPTED")
    object TIMEOUT : Reason("TIMEOUT")
    object CANCELED : Reason("CANCELED")
    object API_NOT_CONNECTED : Reason("API_NOT_CONNECTED")
    object DEAD_CLIENT : Reason("DEAD_CLIENT")

    companion object Factory {
        fun byCode(statusCode: Int): GoogleLoginError {
            val reason: Reason = when (statusCode) {
                2 -> SERVICE_VERSION_UPDATE_REQUIRED
                3 -> SERVICE_DISABLED
                4 -> SIGN_IN_REQUIRED
                5 -> INVALID_ACCOUNT
                6 -> RESOLUTION_REQUIRED
                7 -> NETWORK_ERROR
                8 -> INTERNAL_ERROR
                10 -> DEVELOPER_ERROR
                13 -> ERROR
                14 -> INTERRUPTED
                15 -> TIMEOUT
                16 -> CANCELED
                17 -> API_NOT_CONNECTED
                18 -> DEAD_CLIENT
                else -> NO_LOGIN
            }
            return GoogleLoginError(reason)
        }
    }
}
