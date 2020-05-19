package com.example.bloodbank.view.fragment.registerFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.network.models.DateTxt;
import com.example.bloodbank.network.models.login.Auth;

import com.example.bloodbank.util.General;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.loginFragment.LoginFragment;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.network.api.APIClient.getClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {


    private EditText registerFragmentETName;
    private EditText registerFragmentETEmail;
    private TextView registerFragmentETBirthDate;
    private TextView registerFragmentETHistory;
    private EditText registerFragmentETPhone;
    private EditText registerFragmentETPassword;
    private EditText registerFragmentETConfirmPassword;
    private Spinner registerFragmentETBloodTypes;
    private Spinner registerFragmentETCity;
    private Spinner registerFragmentETGovernorates;
    private SpinnerAdapter GovernoratesAdapter, CitiesAdapter, bloodTypesAdapter;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initFragment();

        bloodTypesAdapter = new SpinnerAdapter(getActivity());
        General.getSpinnerData(getActivity(), registerFragmentETBloodTypes, bloodTypesAdapter, getString(R.string.اختر_فصيله_الدم), getClient().getBloodType());

        GovernoratesAdapter = new SpinnerAdapter(getActivity());
        CitiesAdapter = new SpinnerAdapter(getActivity());
        General.getSpinnerData(getActivity(),registerFragmentETGovernorates,GovernoratesAdapter,getString(R.string.Wait),getClient().getGovernorates(),registerFragmentETCity,
                CitiesAdapter,getString(R.string.Wait),getClient().getCities(GovernoratesAdapter.selectedId));

        initView(view);
        return view;
    }

    private void initView(View view) {
        registerFragmentETCity =  view.findViewById(R.id.register_fragment_ET_city);
        registerFragmentETGovernorates =  view.findViewById(R.id.register_fragment_ET_Governorates);
        registerFragmentETBloodTypes =  view.findViewById(R.id.register_fragment_ET_blood_spinner);
        registerFragmentETBirthDate = view.findViewById(R.id.register_fragment_ET_birth_date);
        registerFragmentETName = view.findViewById(R.id.register_fragment_ET_name);
        registerFragmentETEmail = view.findViewById(R.id.register_fragment_ET_email);
        registerFragmentETPassword = view.findViewById(R.id.register_fragment_ET_Password);
        registerFragmentETPhone = view.findViewById(R.id.register_fragment_ET_Phone);
        registerFragmentETConfirmPassword = view.findViewById(R.id.register_fragment_ET__confirm_password);
        registerFragmentETHistory = view.findViewById(R.id.register_fragment_ET_history);
        Button registerFragmentBtnRegister = view.findViewById(R.id.register_fragment_btn_register);


        registerFragmentETBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Birth Date";
                DateTxt dateTxt = new DateTxt("01", "01", "1950", "1950-01-01");
                HelperMethod.showCalender(getContext(), title, registerFragmentETBirthDate, dateTxt);
            }
        });
        registerFragmentETHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Donation Last Date";
                DateTxt dateTxt = new DateTxt("01", "02", "2017", "2017-01-01");
                HelperMethod.showCalender(getContext(), title, registerFragmentETHistory, dateTxt);
            }
        });

        registerFragmentBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerClient();
            }
        });


    }

    private void registerClient() {
        String name = registerFragmentETName.getText().toString();
        String email = registerFragmentETEmail.getText().toString();
        String birthDate = registerFragmentETBirthDate.getText().toString();
        String phone = registerFragmentETPhone.getText().toString();
        String password = registerFragmentETPassword.getText().toString();
        String history = registerFragmentETName.getText().toString();
        String confirmPassword = registerFragmentETConfirmPassword.getText().toString();
        String Governorates = registerFragmentETGovernorates.getSelectedItem().toString();
        String BloodTypes = registerFragmentETBloodTypes.getSelectedItem().toString();

        getClient().register(name, email, birthDate, Governorates, phone, history, password, confirmPassword, BloodTypes).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NotNull Call<Auth> call, @NotNull Response<Auth> response) {

                assert response.body() != null;
                if (response.body().getStatus() == 1) {

                    HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                            R.id.user_cycle_activity, new LoginFragment());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Auth> call, @NotNull Throwable t) {

            }
        });
    }


    @Override
    public void onBack() {
        HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), R.id.user_cycle_activity, new LoginFragment());
    }


}












