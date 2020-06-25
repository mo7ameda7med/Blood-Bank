package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostAdapter;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.network.models.posts.PostsData;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.loginFragment.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostsDetailsFragment extends BaseFragment {

    @BindView(R.id.PostsDetailsFragment_post_image)
    ImageView PostsDetailsFragmentPostImage;
    @BindView(R.id.PostsDetailsFragment_iv_favorite)
    ImageView PostsDetailsFragmentIvFavorite;
    @BindView(R.id.PostsDetailsFragment_tv_details)
    TextView PostsDetailsFragmentTvDetails;
    @BindView(R.id.PostsDetailsFragment_tv_info)
    TextView PostsDetailsFragmentTvInfo;

    public PostsData post;

    public PostsDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_posts_details, container, false);
        initFragment();
        ButterKnife.bind(this, view);
        setData();
        return view;
    }

    private void setData() {
        PostsDetailsFragmentTvInfo.setText(post.getContent());
        PostsDetailsFragmentTvDetails.setText(post.getTitle());
        HelperMethod.onLoadImageFromUrl(PostsDetailsFragmentPostImage, post.getThumbnailFullPath(), getActivity());
        if (post.getIsFavourite()) {
            PostsDetailsFragmentIvFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        } else {
            PostsDetailsFragmentIvFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
        }
    }

    public void onBack() {
        HelperMethod.replaceFragment(getFragmentManager(), R.id.user_cycle_activity, new HomeFragment());

    }

}
