package com.example.bloodbank.view.fragment.passwordFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.newPassword.NewPassword;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.passwordFragment.ConfirmPasswordFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForgetPasswordFragment extends BaseFragment {


    private EditText forgetPasswordFragmentETPhone;
    private Button loginFragmentBtnLogin;
    private ApiService apiService;

    public ForgetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_forget_passwod, container, false);
        apiService= APIClient.getClient().create(ApiService.class);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        forgetPasswordFragmentETPhone=view.findViewById(R.id.forget_password_fragment_ET_Phone);
        loginFragmentBtnLogin=view.findViewById(R.id.login_fragment_btn_login);
        loginFragmentBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Send();
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),R.id.user_cycle_activity,new ConfirmPasswordFragment());
            }
        });
    }

    private void Send()
    {

        String phone =forgetPasswordFragmentETPhone.getText().toString();

        apiService.resetPassword(phone).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(@NotNull Call<NewPassword> call, @NotNull Response<NewPassword> response) {
                assert response.body() != null;
                if(response.body().getStatus()==1)
                {
                    Toast.makeText(getActivity(),response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<NewPassword> call, @NotNull Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }

}
