package ua.com.itteam.login


interface LoginView {
    fun onLoginSuccess(message: String)
    fun onLoginError(message: String)
}