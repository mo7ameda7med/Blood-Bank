package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.network.models.posts.PostsData;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;

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
    private List<PostsData> posts = new ArrayList<>();

    private PostFragment postFragment = new PostFragment();


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
        assert getArguments() != null;
        posts = (List<PostsData>) getArguments().getSerializable("Posts");
        setupData();
        return view;
    }

    private void setupData() {




        for (int i = 0; i <= posts.size(); i++) {
            HelperMethod.onLoadImageFromUrl(PostsDetailsFragmentPostImage,
                    posts.get(i).getThumbnailFullPath(), getContext());
            PostsDetailsFragmentTvDetails.setText(posts.get(i).getTitle());
            PostsDetailsFragmentTvDetails.setText(posts.get(i).getTitle());
            break;
        }

    }
}
