package ua.com.itteam.fb

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.Profile
import ua.com.itteam.R
import ua.com.itteam.databinding.ActivityFbBinding


class FBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFbBinding>(this,R.layout.activity_fb)
        binding.profile=Profile.getCurrentProfile()
    }
}