package com.jetradarmobile.sociallogin_vkontakte

import com.jetradarmobile.sociallogin.SocialLoginError


class VkLoginError(reason: Reason) : SocialLoginError(reason) {
    object NO_LOGIN : Reason("Vk token receiving error")
}