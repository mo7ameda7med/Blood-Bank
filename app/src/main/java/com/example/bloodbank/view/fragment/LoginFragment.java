package com.example.bloodbank.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.login.Client;
import com.example.bloodbank.network.models.login.Login;
import com.example.bloodbank.network.services.ApiService;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {


    @BindView(R.id.login_fragment_ET_Phone)
    EditText loginFragmentETPhone;
    @BindView(R.id.login_fragment_ET_Password)
    EditText loginFragmentETPassword;
    @BindView(R.id.login_fragment_btn_forget_password)
    Button loginFragmentBtnForgetPassword;
    @BindView(R.id.login_fragment_btn_login)
    Button loginFragmentBtnLogin;
    @BindView(R.id.login_fragment_checkBox)
    CheckBox loginFragmentCheckBox;
    private ApiService apiService;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        apiService= APIClient.getClient().create(ApiService.class);
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }


        @OnClick(R.id.login_fragment_btn_login)
    public void onViewClicked() {
            String password=loginFragmentETPassword.getText().toString();
            String phone=loginFragmentETPhone.getText().toString();
            Login(new Client(phone,password));

        }
        private void Login(Client login){
        apiService.loginClient(login).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body().getStatus()==1)
                {
                    Toast.makeText(getActivity(), "loginClient", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }



    }



