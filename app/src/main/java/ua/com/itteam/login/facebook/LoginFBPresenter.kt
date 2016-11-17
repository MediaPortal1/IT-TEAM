package ua.com.itteam.login.facebook

import android.content.Intent
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback

interface LoginFBPresenter {
    fun onLoginBtnClick()
    fun onLoginSuccessAction()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
}