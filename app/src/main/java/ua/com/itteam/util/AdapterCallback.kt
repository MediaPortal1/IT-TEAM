package ua.com.itteam.util


interface AdapterCallback {
    fun getItemCount(): Int
    fun isLoading(): Boolean
    fun setLoading(loading: Boolean)
    fun loadWallPosts(offset: Int = 0)
}