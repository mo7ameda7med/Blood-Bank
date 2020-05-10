package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.util.HelperMethod;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> implements Filterable {
    @BindView(R.id.item_post_IV_post_image)
    ImageView itemPostIVPostImage;
    @BindView(R.id.item_post_IB_favorite)
    ImageButton itemPostIBFavorite;
    @BindView(R.id.item_post_IB_un_favorite)
    ImageButton itemPostIBUnFavorite;
    @BindView(R.id.item_post_Btn_posts)
    Button itemPostBtnPosts;
    private Context context;
    private Activity activity;
    private List<Posts> posts;
    private List<Posts> postsFull;
//    private List<RestaurantClientData> restaurantDataList = new ArrayList<>();

    public PostAdapter(Activity activity, List<Posts> posts) {
        this.activity = activity;
        this.posts = posts;
        postsFull = new ArrayList<>(posts);
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

    private void setData(PostAdapter.ViewHolder holder, int position) {
        HelperMethod.onLoadImageFromUrl(itemPostIVPostImage,postsFull.get(position).getData().getData().get(0).getThumbnailFullPath(),context);

    }

    private void setAction(PostAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public Filter getFilter() {
        return postFilter;
    }

    private Filter postFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Posts> postsList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                postsList.addAll(postsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Posts post : postsFull) {
                    if (post.getData().getData().get(0).getTitle().toLowerCase().contains(filterPattern))
                    {
                        postsList.add(post);
                    }
                }
            }
            FilterResults filterResults= new FilterResults();
            filterResults.values=postsList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            posts.clear();
            posts.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;


        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);


        }
    }
}
