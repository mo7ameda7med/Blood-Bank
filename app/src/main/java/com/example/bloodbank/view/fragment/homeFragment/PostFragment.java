package com.example.bloodbank.view.fragment.homeFragment;


import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostAdapter;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.network.models.posts.PostsData;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.OnEndLess;
import com.example.bloodbank.view.fragment.BaseFragment;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.network.api.APIClient.getClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends BaseFragment {
//        implements Filterable {


    @BindView(R.id.fragment_post_sp_category)
    Spinner fragmentPostSpCategory;
    @BindView(R.id.fragment_post_ET_Search)
    EditText fragmentPostETSearch;
    @BindView(R.id.fragment_post_RV_post)
    RecyclerView fragmentPostRVPost;
    private PostAdapter postAdapter;
    private List<PostsData> posts = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndLess onEndLess;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        initFragment();

        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this,view);
        setupRv();
        getPost(1);
        return view;

    }




//    @Override
//    public Filter getFilter() {
//        return postFilter;
//    }

//    private Filter postFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            List<Posts> postsList = new ArrayList<>();
//            if (constraint == null || constraint.length() == 0) {
//                postsList.addAll(posts);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Posts post : posts) {
//                    if (post.getData().getData().get(0).getTitle().toLowerCase().contains(filterPattern)) {
//                        postsList.add(post);
//                    }
//                }
//            }
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = postsList;
//
//            return filterResults;
//        }

//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            posts.clear();
//            posts.addAll((List) results.values);
//            postAdapter.notifyDataSetChanged();
//        }
//    };


    private void getPost(int page) {
        getClient().getPost("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(@NotNull Call<Posts> call, @NotNull Response<Posts> response) {
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    posts.addAll(response.body().getData().getData());
                }

            }

            @Override
            public void onFailure(@NotNull Call<Posts> call, @NotNull Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRv() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity())
                .getApplicationContext(),
                LinearLayoutManager.VERTICAL
                , false);
        fragmentPostRVPost.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getPost(current_page);

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }

            }
        };
        fragmentPostRVPost.addOnScrollListener(onEndLess);
        postAdapter = new PostAdapter(getContext(), getActivity(), posts);
        fragmentPostRVPost.setAdapter(postAdapter);

    }
}
