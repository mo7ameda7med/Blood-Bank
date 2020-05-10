package com.example.bloodbank.view.fragment.homeFragment.postsFragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostAdapter;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.posts.Posts;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.OnEndLess;
import com.example.bloodbank.view.fragment.BaseFragment;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private PostAdapter postAdapter;
    private List<Posts> posts=new ArrayList<>();
    private ApiService apiService;
    private Integer maxPage=0;
    private OnEndLess onEndLess;

    public PostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        initFragment();
        apiService= APIClient.getClient().create(ApiService.class);
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

//    private void search() {
//        fragmentPostETSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//
//            private void filter(String post) {
//
//            }
//        });
//    }
    private void getPost(int page)
    {
        apiService.getPost("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27",page).enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(@NotNull Call<Posts> call, @NotNull Response<Posts> response) {
                assert response.body() != null;
                if(response.body().getStatus()==1)
                {
                    setupRv();
                    maxPage=response.body().getData().getLastPage();
                    postAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(@NotNull Call<Posts> call, @NotNull Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
private void setupRv()
{
    postAdapter=new PostAdapter(getActivity(),posts);
    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(Objects.requireNonNull(getActivity())
            .getApplicationContext(),
            LinearLayoutManager.HORIZONTAL
            ,false);

    fragmentPostRVPost.setLayoutManager(linearLayoutManager);
    fragmentPostRVPost.setAdapter(postAdapter);
    onEndLess=new OnEndLess(linearLayoutManager,1) {
        @Override
        public void onLoadMore(int current_page) {
            if(current_page <= maxPage)
            {
                if(maxPage !=0 && current_page !=1)
                {
                    onEndLess.previous_page=current_page;
                    getPost(current_page);

                }else {
                    onEndLess.current_page=onEndLess.previous_page;
                }
            }

        }
    };
    fragmentPostRVPost.addOnScrollListener(onEndLess);

}

}
