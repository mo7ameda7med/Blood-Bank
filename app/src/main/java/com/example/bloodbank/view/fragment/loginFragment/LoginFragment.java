package com.example.bloodbank.view.fragment.loginFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.login.Auth;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.activity.MainActivity;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.registerFragment.RegisterFragment;
import com.example.bloodbank.view.fragment.passwordFragment.ForgetPasswordFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.login_fragment_btn_login)
    Button loginFragmentBtnLogin;
    @BindView(R.id.login_fragment_tv_remember)
    TextView loginFragmentTvRemember;
    @BindView(R.id.login_fragment_checkBox)
    CheckBox loginFragmentCheckBox;
    @BindView(R.id.login_fragment_btn_register)
    Button loginFragmentBtnRegister;
    private ApiService apiService;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        apiService = APIClient.getClient().create(ApiService.class);
        initFragment();
        return view;
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    private void Login() {
        String password = loginFragmentETPassword.getText().toString();
        String phone = loginFragmentETPhone.getText().toString();
        apiService.login(phone, password).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NotNull Call<Auth> call, @NotNull Response<Auth> response) {

            }

            @Override
            public void onFailure(@NotNull Call<Auth> call, @NotNull Throwable t) {

            }
        });
    }

    @OnClick({R.id.login_fragment_btn_forget_password, R.id.login_fragment_btn_login, R.id.login_fragment_btn_register})
    void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_btn_forget_password:
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), R.id.user_cycle_activity, new ForgetPasswordFragment());

                break;
            case R.id.login_fragment_btn_login:
                Login();
                startActivity(new Intent(getActivity(),MainActivity.class));
                break;
            case R.id.login_fragment_btn_register:
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), R.id.user_cycle_activity, new RegisterFragment());
                break;
        }
    }
}



