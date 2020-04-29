package com.example.bloodbank.view.fragment;

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

import com.example.bloodbank.network.models.login.Client;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmPasswordFragment extends BaseFragment {


    EditText confirmPasswordFragmentETCode;
    EditText confirmPasswordFragmentETPassword;
    EditText forgetPasswordFragmentETConfirmPassword;
    Button confirmPasswordFragmentBtnLogin;
    private ApiService apiService;

    public ConfirmPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_confirm_password, container, false);
        apiService= APIClient.getClient().create(ApiService.class);
        initFragment();
        intiView(view);
        return view;
    }
   private void intiView(View view)
    {
        confirmPasswordFragmentETCode=view.findViewById(R.id.confirm_password_fragment_ET_code);
        confirmPasswordFragmentETPassword=view.findViewById(R.id.confirm_password_fragment_ET_Password);
        forgetPasswordFragmentETConfirmPassword=view.findViewById(R.id.confirm_password_fragment_ET_confirm_password);
        confirmPasswordFragmentBtnLogin=view.findViewById(R.id.confirm_password_fragment_btn);
        confirmPasswordFragmentBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code =confirmPasswordFragmentETCode.getText().toString();
                String password =confirmPasswordFragmentETPassword.getText().toString();
                String ConfirmPassword =forgetPasswordFragmentETConfirmPassword.getText().toString();
                newPassword(new Client(code,password,ConfirmPassword));
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),R.id.user_cycle_activity,new LoginFragment());
            }
        });

    }
    private void newPassword(Client newPassword)
    {
        apiService.newPassword(newPassword).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(@NotNull Call<Client> call, @NotNull Response<Client> response) {
                assert response.body() != null;
                if(response.body().getStatus()==1)
                {
                    Toast.makeText(getActivity(), "reset", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Client> call, @NotNull Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        super.onBack();
    }
}
