package ua.com.itteam.vk.model

import ua.com.itteam.vk.presenter.VkPresenterCallback


interface VkModel {
    fun getUserInfo(callback: VkPresenterCallback)
    fun getUserNews(callback: VkPresenterCallback)
}