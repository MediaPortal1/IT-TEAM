package ua.com.itteam.vk.presenter

import com.vk.sdk.api.methods.VKApiWall
import com.vk.sdk.api.model.VKApiPost
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKWallPostResult


interface VkPresenterCallback {
    fun profileInfoLoaded(profileInfo: VKApiUserFull)
    fun profileNewsLoaded(wall: List<VKApiPost>)

}