package ua.com.itteam.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.vk.sdk.VKSdk
import com.vk.sdk.util.VKUtil
import kotlinx.android.synthetic.main.activity_login.*
import ua.com.itteam.R
import ua.com.itteam.login.LoginView
import ua.com.itteam.login.facebook.LoginFBPresenter
import ua.com.itteam.login.facebook.LoginFBPresenterImpl
import ua.com.itteam.login.vk.LoginVKPresenter
import ua.com.itteam.login.vk.LoginVKPresenterImpl

class LoginActivity : AppCompatActivity(), LoginView {

    lateinit var vkPresenter: LoginVKPresenter
    lateinit var fbPresenter: LoginFBPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vkPresenter = LoginVKPresenterImpl(this)
        fbPresenter = LoginFBPresenterImpl(login_btn_fb,this,this)
        initViews()
    }

    private fun initViews(){
        login_btn_vk.setOnClickListener { vkPresenter.onLoginBtnClick()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(VKSdk.onActivityResult(requestCode,resultCode,data, vkPresenter.getVkLoginCallback()))
            else if(fbPresenter.onActivityResult(requestCode,resultCode,data))
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginSuccess(message: String) {
        Toast.makeText(this,"Login successful, id: " + message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoginError(message: String) {
        Toast.makeText(this,"Login failed, error:  " + message, Toast.LENGTH_SHORT).show()
    }
}