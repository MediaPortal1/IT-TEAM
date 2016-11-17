package ua.com.itteam.login.facebook

import android.app.Activity
import android.content.Intent
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import ua.com.itteam.fb.FBActivity
import ua.com.itteam.login.LoginView
import java.util.*
import android.os.Bundle




class LoginFBPresenterImpl(val button: LoginButton, val view: LoginView, val activity: Activity) : LoginFBPresenter {

    companion object{
        val FACEBOOK_USER="facebook_user"
    }

    private var callbackManager: CallbackManager

    init {
        callbackManager=CallbackManager.Factory.create()
        button.setOnClickListener { onLoginBtnClick() }
        button.setReadPermissions(Arrays.asList("public_profile","email"))
        button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                val intent=Intent(activity.baseContext,FBActivity::class.java)
                activity.startActivity(intent)
            }
            override fun onCancel() {

            }

            override fun onError(error: FacebookException?) {
                view.onLoginError(error.toString())
            }
        })

    }

    override fun onLoginBtnClick(){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) = callbackManager.onActivityResult(requestCode, resultCode, data)
    override fun onLoginSuccessAction() {

    }
}