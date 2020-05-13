package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.util.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class

PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<Posts> posts = new ArrayList<>();

//    private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    public PostAdapter(Context context, Activity activity, List<Posts> posts) {
        this.context = context;
        this.activity = activity;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        HelperMethod.onLoadImageFromUrl(holder.itemPostIVPostImage, posts.get(position).getData().getData().get(position).getThumbnailFullPath(), context);
        holder.itemPostBtnPosts.setText(posts.get(position).getData().getData().get(position).getTitle());

    }

    private void setAction(ViewHolder holder, int position) {

    }
    public void filterList(ArrayList<Posts> postsArrayList) {
        posts = postsArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.item_post_IV_post_image)
        ImageView itemPostIVPostImage;
        @BindView(R.id.item_post_IB_favorite)
        ImageButton itemPostIBFavorite;
        @BindView(R.id.item_post_Btn_posts)
        Button itemPostBtnPosts;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        @OnClick({R.id.item_post_IB_favorite})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.item_post_IB_favorite:

                    boolean isFavourite = true;

                    if (isFavourite) {
                        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    } else {
                        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
                    }

                    break;
            }
        }
    }
}
