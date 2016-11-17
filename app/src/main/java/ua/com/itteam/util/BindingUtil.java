package ua.com.itteam.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.vk.sdk.api.model.VKApiPost;
import com.vk.sdk.api.model.VKAttachments;


public class BindingUtil {

    @BindingAdapter("bind:loadImage")
    public static void loadImage(ImageView view,String url){
        Picasso.with(view.getContext())
                    .load(url)
                    .into(view);
    }

}
