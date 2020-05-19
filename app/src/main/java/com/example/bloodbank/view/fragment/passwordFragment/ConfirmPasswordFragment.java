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
import com.example.bloodbank.network.models.newPassword.NewPassword;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.loginFragment.LoginFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.network.api.APIClient.getClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmPasswordFragment extends BaseFragment {


    @BindView(R.id.confirm_password_fragment_ET_code)
    EditText confirmPasswordFragmentETCode;
    @BindView(R.id.confirm_password_fragment_ET_Password)
    EditText confirmPasswordFragmentETPassword;
    @BindView(R.id.confirm_password_fragment_ET_confirm_password)
    EditText confirmPasswordFragmentETConfirmPassword;
    @BindView(R.id.confirm_password_fragment_btn)
    Button confirmPasswordFragmentBtn;
    String phone;
    public ConfirmPasswordFragment(String phone) {
        this.phone = phone;
    }





    public ConfirmPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_confirm_password, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    private void newPassword() {
        String code = confirmPasswordFragmentETCode.getText().toString();
        String password = confirmPasswordFragmentETPassword.getText().toString();
        String ConfirmPassword = confirmPasswordFragmentETConfirmPassword.getText().toString();

        getClient().newPassword(phone,code, password, ConfirmPassword).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(@NotNull Call<NewPassword> call, @NotNull Response<NewPassword> response) {
                HelperMethod.dismissProgressDialog();
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),R.id.user_cycle_activity,new LoginFragment());
                }
                else
                {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(@NotNull Call<NewPassword> call, @NotNull Throwable t) {

            }
        });

    }
    @OnClick(R.id.confirm_password_fragment_btn)
    public void onViewClicked() {
        newPassword();
    }

    @Override
    public void onBack() {
    }



}



