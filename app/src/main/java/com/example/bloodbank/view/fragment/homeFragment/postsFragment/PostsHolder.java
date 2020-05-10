package com.example.bloodbank.view.fragment.homeFragment.postsFragment;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.util.HelperMethod;

public class PostsHolder extends RecyclerView.ViewHolder {
    private ImageView postImage;

    private ImageButton favorite;
    private ImageButton unFavorite;

    private TextView postBtn;
    public PostsHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View view) {
        postImage = view.findViewById(R.id.item_post_IV_post_image);
        favorite = view.findViewById(R.id.item_post_IB_favorite);
        unFavorite =view.findViewById(R.id.item_post_IB_un_favorite);
        postBtn=view.findViewById(R.id.item_post_Btn_posts);
    }
    private void viewBind(Posts posts,Context context)
    {

//        HelperMethod.onLoadImageFromUrl(postImage,posts.getData().getData().get(0).getThumbnailFullPath(),context);



    }
}

