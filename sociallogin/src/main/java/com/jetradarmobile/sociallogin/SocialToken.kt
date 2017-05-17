package com.jetradarmobile.sociallogin

/**
 * Data class that store user data from social network
 *
 * @property token authorization token provided by social network api
 * @property secret oauth secret token, used in twitter
 * @property userId user identifier in social network
 * @property userName user display name
 * @property email user email
 */
data class SocialToken(val token: String,
                       val secret: String = "",
                       val userId: String = "",
                       val userName: String = "",
                       val email: String = "")
