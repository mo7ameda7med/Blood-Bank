package com.example.bloodbank.view.fragment.homeFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostAdapter;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.network.models.posts.PostsData;
import com.example.bloodbank.util.General;
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


    @BindView(R.id.fragment_post_sp_category)
    Spinner fragmentPostSpCategory;
    @BindView(R.id.fragment_post_ET_Search)
    EditText fragmentPostETSearch;
    @BindView(R.id.fragment_post_RV_post)
    RecyclerView fragmentPostRVPost;
    private List<PostsData> posts = new ArrayList<>();
    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private SpinnerAdapter categoriesAdapter;
    private PostAdapter postAdapter;
    private boolean filter = false;


    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        ButterKnife.bind(this, view);

        setupRv();
        getPost(1, getClient().getPost("mg1i1XHW5bHMJzjxi6ymJbVOflHiaCH5v8cYjS1aOaMphzubY4DtOsyrtIUf", 1));

        categoriesAdapter = new SpinnerAdapter(getActivity());
        General.getCategories(getActivity(), fragmentPostSpCategory, categoriesAdapter, getString(R.string.Wait), getClient().getCategories());

        fragmentPostETSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFilter(1);
            }
        });


        return view;

    }

    private void onFilter(int page) {
        filter = true;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        onEndLess.totalItemCount = 0;
        maxPage = 0;
        postAdapter = new PostAdapter(getContext(), getActivity(), posts);
        fragmentPostRVPost.setAdapter(postAdapter);
        Call<Posts> call = getClient().getPostsFilter("mg1i1XHW5bHMJzjxi6ymJbVOflHiaCH5v8cYjS1aOaMphzubY4DtOsyrtIUf", 1
                , fragmentPostETSearch.getText().toString(),
                categoriesAdapter.selectedId);
        getPost(page, call);
    }


    private void getPosts(int page) {
        Call<Posts> call = getClient().getPost("mg1i1XHW5bHMJzjxi6ymJbVOflHiaCH5v8cYjS1aOaMphzubY4DtOsyrtIUf", 1);
        getPost(page, call);
    }

    private void getPost(int page, Call<Posts> call) {
        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(@NotNull Call<Posts> call, @NotNull Response<Posts> response) {
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    maxPage = response.body().getData().getLastPage();
                    posts.addAll(response.body().getData().getData());
                    postAdapter.notifyDataSetChanged();
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
                        if (filter) {
                            onFilter(current_page);
                        } else {
                            getPosts(current_page);
                        }

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


    public void onBack() {
        super.onBack();
    }

}
