package ua.com.itteam.vk.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.vk.sdk.api.methods.VKApiWall
import com.vk.sdk.api.model.VKApiPost
import com.vk.sdk.api.model.VKApiUserFull
import com.vk.sdk.api.model.VKWallPostResult
import ua.com.itteam.util.AdapterCallback
import ua.com.itteam.vk.model.VkModel
import ua.com.itteam.vk.model.VkModelImpl
import ua.com.itteam.vk.view.VkView
import ua.com.itteam.util.WallAdapter
import java.util.*


class VkPresenterImpl(val context: Context, val view: VkView) : VkPresenter, VkPresenterCallback, AdapterCallback {

    private val model: VkModel = VkModelImpl.instance
    private var wallList = ArrayList<VKApiPost>()
    lateinit private var wallAdapter: WallAdapter<VKApiPost>

    private var isWallInit = false
    private var isRefreshing = false
    private var isLoading = false


    override fun profileInfoLoaded(profileInfo: VKApiUserFull) {
        view.bindProfileInfo(profileInfo)
    }

    override fun loadUserInfo() {
        model.getUserInfo(this)
    }

    override fun onRefresh() {
        wallList.clear()
        isRefreshing = true
        loadWallPosts()
    }


    override fun profileNewsLoaded(wall: List<VKApiPost>) {
        wallList.addAll(wall)
        if (!isWallInit) {
            initUserNews()
        } else wallAdapter.notifyDataSetChanged()

        if (isRefreshing) {
            isRefreshing = false
            view.setRefreshing(false)
        }
        isLoading = false
    }

    override fun initUserNews() {
        val recycler = view.getWallRecycler()
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        wallAdapter = WallAdapter(wallList, recycler, this)
        recycler.adapter = wallAdapter
        isWallInit = true
        loadWallPosts()
    }

    override fun loadWallPosts(offset: Int) {
        isLoading = true
        model.getUserNews(this)
    }

    override fun getItemCount() = wallList.size

    override fun isLoading() = isLoading

    override fun setLoading(loading: Boolean) {
        isLoading = loading
    }
}