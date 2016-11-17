package ua.com.itteam.vk.view

import android.support.v7.widget.RecyclerView
import com.vk.sdk.api.model.VKApiUserFull

interface VkView {
    fun bindProfileInfo(profileInfo: VKApiUserFull)
    fun getWallRecycler(): RecyclerView
    fun setRefreshing(status: Boolean)
}