package ua.com.itteam.vk.model

import android.util.Log
import com.vk.sdk.api.*
import com.vk.sdk.api.model.*
import org.json.JSONObject
import ua.com.itteam.vk.presenter.VkPresenterCallback
import java.util.*

class VkModelImpl private constructor() : VkModel {

    companion object {
        val instance = VkModelImpl()
    }

    private var nextFrom: String = ""
    private var count=20

    override fun getUserInfo(presenterCallback: VkPresenterCallback) {
        val params = VKParameters()
        val request = VKRequest("users.get", VKParameters.from(VKApiConst.FIELDS, "photo_100,status,bdate,screen_name,home_town"),
                VKUsersArray::class.java)

        request.executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)
                val users = response?.parsedModel as VKUsersArray
                val user = users[0]
                Log.d("VKUSER", user.toString())
                presenterCallback.profileInfoLoaded(user)
            }
        })
    }

    override fun getUserNews(callback: VkPresenterCallback) {
        val request=VKRequest("newsfeed.get", VKParameters.from(VKApiConst.COUNT, count.toString(), "next_from",nextFrom))

        request.executeWithListener(object : VKRequest.VKRequestListener() {
            override fun onComplete(response: VKResponse?) {
                super.onComplete(response)

                val responseJSON = response?.json?.optJSONObject("response")
                val wallItems = responseJSON?.getJSONArray("items")

                if (responseJSON != null && wallItems != null) {
                    val listItems = (0..count-1).map { VKApiPost(wallItems.getJSONObject(it)) }
                    nextFrom=responseJSON.getString("next_from")
                    callback.profileNewsLoaded(listItems)
                }
            }
        })

    }

}