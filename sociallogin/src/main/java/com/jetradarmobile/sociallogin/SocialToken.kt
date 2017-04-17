package com.jetradarmobile.sociallogin

/**
 * Data class that store user data from social network
 *
 * @property token authorization token provided by social network api
 * @property userId user identifier in social network
 * @property userName user display name
 */
data class SocialToken(val token: String,
                       val userId: String,
                       val userName: String)
