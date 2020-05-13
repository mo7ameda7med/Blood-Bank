package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.network.models.posts.PostsData;
import com.example.bloodbank.network.models.toggleFavourite.ToggleFavourite;
import com.example.bloodbank.util.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.network.api.APIClient.getClient;

public class

PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<PostsData> posts = new ArrayList<>();


    public PostAdapter(Context context, Activity activity, List<PostsData> posts) {
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
        holder.position= position;
        HelperMethod.onLoadImageFromUrl(holder.itemPostIVPostImage, posts.get(position).getThumbnailFullPath(), context);
        holder.itemPostBtnPosts.setText(posts.get(position).getTitle());

        if (posts.get(position).getIsFavourite()) {
            holder.itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
        } else {
            holder.itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
        }

    }

    private void setAction(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position;
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
                    posts.get(position).setIsFavourite(!posts.get(position).getIsFavourite());
                    if (posts.get(position).getIsFavourite()) {
                        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                    } else {
                        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
                    }
                    getClient().ToggleFavourite(posts.get(position).getId(),"Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27").enqueue(new Callback<ToggleFavourite>() {
                        @Override
                        public void onResponse(Call<ToggleFavourite> call, Response<ToggleFavourite> response) {
                            if(response.body().getStatus()==1)
                            {
                            }
                        }

                        @Override
                        public void onFailure(Call<ToggleFavourite> call, Throwable t) {

                        }
                    });


                    break;
            }
        }
    }
}
