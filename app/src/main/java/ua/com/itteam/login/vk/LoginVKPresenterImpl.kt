package ua.com.itteam.login.vk

import android.app.Activity
import android.content.Intent
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
import ua.com.itteam.login.LoginView
import ua.com.itteam.vk.view.VkActivity


class LoginVKPresenterImpl(val activity: Activity, view: LoginView = activity as LoginView): LoginVKPresenter {

    private var callback: VKCallback<VKAccessToken>
    lateinit private var token: VKAccessToken

    init {
        callback = object : VKCallback<VKAccessToken> {
            override fun onError(error: VKError?) {
                view.onLoginError(error.toString())
            }

            override fun onResult(res: VKAccessToken?) {
                if(res!=null) {
                    token = res
                    view.onLoginSuccess(token.userId)
                    onLoginSuccessAction()
                }
            }
        }
    }

    override fun onLoginBtnClick() {
        VKSdk.login(activity, VKScope.WALL, VKScope.STATUS, VKScope.FRIENDS)
    }

    override fun getVkLoginCallback(): VKCallback<VKAccessToken> = callback

    override fun onLoginSuccessAction() {
        activity.startActivity(Intent(activity.baseContext, VkActivity::class.java))
    }
}