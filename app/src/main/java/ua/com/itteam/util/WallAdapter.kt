package ua.com.itteam.util

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vk.sdk.api.methods.VKApiWall
import com.vk.sdk.api.model.VKApiPost
import ua.com.itteam.R
import ua.com.itteam.databinding.ItemWallPostBinding
import ua.com.itteam.vk.presenter.VkPresenter


class WallAdapter<WallItem>(val wallItems: List<WallItem>,recyclerView: RecyclerView,
                  val presenter: AdapterCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_LOADING = 0
    val VIEW_TYPE_POST_VK = 1
    val VIEW_TYPE_POST_FB = 2

    val ADAPTER_TYPE_VK = 1
    val ADAPTER_TYPE_FB = 2

    init {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var previousTotal = 0
            private val visibleThreshold = 10
            internal var firstVisibleItem: Int = 0
            internal var visibleItemCount: Int = 0
            internal var totalItemCount: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.childCount
                totalItemCount = linearLayoutManager.itemCount
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
                val lastcountitem = linearLayoutManager.findLastVisibleItemPosition()
                if (totalItemCount !in 0..1) {
                    if (presenter.isLoading()) {
                        if (totalItemCount > previousTotal) {
                            presenter.setLoading(false)
                            previousTotal = totalItemCount
                        }
                    }
                    if (!presenter.isLoading() && firstVisibleItem + visibleThreshold >= totalItemCount - visibleItemCount) {
                        presenter.loadWallPosts()
                    }
                }
            }
        })
    }

    private fun getAdapterPostType() = if(wallItems[0] is VKApiPost) ADAPTER_TYPE_VK else ADAPTER_TYPE_FB

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder){
            is VkHolderPost -> holder.bindView.post = wallItems[position] as VKApiPost
//            is FBHolderPost -> holder.bindView.post = wallItems[position] as FacebookPost
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = when (viewType) {
        VIEW_TYPE_POST_FB -> null
        VIEW_TYPE_POST_VK -> VkHolderPost(DataBindingUtil.inflate<ItemWallPostBinding>(LayoutInflater.from(parent?.context), R.layout.item_wall_post, parent, false))
        VIEW_TYPE_LOADING -> HolderLoading(LayoutInflater.from(parent?.context).inflate(R.layout.item_loading, parent, false))
        else -> null
    }

    override fun getItemCount() = if (presenter.isLoading()) wallItems.size + 1 else wallItems.size

    override fun getItemViewType(position: Int) = if (wallItems.size == position) VIEW_TYPE_LOADING
                                                    else if(getAdapterPostType() == ADAPTER_TYPE_VK) VIEW_TYPE_POST_VK else VIEW_TYPE_POST_FB

    class HolderLoading(itemView: View) : RecyclerView.ViewHolder(itemView)

    class VkHolderPost(val bindView: ItemWallPostBinding) : RecyclerView.ViewHolder(bindView.root)

    class FBHolderPost(val bindView: ItemWallPostBinding) : RecyclerView.ViewHolder(bindView.root)
}
