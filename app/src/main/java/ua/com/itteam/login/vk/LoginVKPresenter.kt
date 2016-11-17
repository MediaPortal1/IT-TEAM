package ua.com.itteam.login.vk

import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback


interface LoginVKPresenter {

    fun onLoginBtnClick()
    fun getVkLoginCallback(): VKCallback<VKAccessToken>
    fun onLoginSuccessAction()
}