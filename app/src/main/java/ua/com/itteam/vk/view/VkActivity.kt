package ua.com.itteam.vk.view

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.vk.sdk.VKSdk
import com.vk.sdk.api.model.VKApiUserFull
import kotlinx.android.synthetic.main.activity_vk.*
import ua.com.itteam.R
import ua.com.itteam.databinding.ActivityVkBinding
import ua.com.itteam.vk.presenter.VkPresenter
import ua.com.itteam.vk.presenter.VkPresenterImpl


class VkActivity : AppCompatActivity(), VkView {

    lateinit var presenter: VkPresenter
    lateinit var binding: ActivityVkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = VkPresenterImpl(baseContext, this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vk)
        initViews()
    }

    private fun initViews() {
        presenter.loadUserInfo()
        presenter.initUserNews()
        fb_refresh.setOnRefreshListener { presenter.onRefresh() }
    }

    override fun bindProfileInfo(profileInfo: VKApiUserFull) {
        binding.profile = profileInfo
    }

    override fun getWallRecycler() = vk_wall_recycler

    override fun setRefreshing(status: Boolean) {
        fb_refresh.isRefreshing = status
    }
}