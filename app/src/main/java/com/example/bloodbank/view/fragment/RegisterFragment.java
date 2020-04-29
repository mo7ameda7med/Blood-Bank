package com.example.bloodbank.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.bloodbank.R;
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.DateTxt;
import com.example.bloodbank.network.models.cities.Cities;
import com.example.bloodbank.network.models.login.Client;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {


    private int governoratesId = 1;
    private EditText registerFragmentETName;
    private EditText registerFragmentETEmail;
    private TextView registerFragmentETBirthDate;
    private TextView registerFragmentETHistory;
    private EditText registerFragmentETPhone;
    private EditText registerFragmentETPassword;
    private EditText registerFragmentETConfirmPassword;
    private Spinner registerFragmentETBloodTypes;
    private Spinner registerFragmentETCity;
    private Spinner registerFragmentETCapital;
    private ApiService apiService;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initFragment();
        initView(view);
        getBloodTypes();
        getGovernorates();
        getCities();
        return view;
    }

    private void initView(View view) {
        apiService = APIClient.getClient().create(ApiService.class);
        registerFragmentETCity = (Spinner) view.findViewById(R.id.register_fragment_ET_city);
        registerFragmentETCapital = (Spinner) view.findViewById(R.id.register_fragment_ET_capital);
        registerFragmentETBloodTypes = (Spinner) view.findViewById(R.id.register_fragment_ET_blood_spinner);
        registerFragmentETBirthDate = view.findViewById(R.id.register_fragment_ET_birth_date);
        registerFragmentETName = view.findViewById(R.id.register_fragment_ET_name);
        registerFragmentETEmail = view.findViewById(R.id.register_fragment_ET_email);
        registerFragmentETPassword = view.findViewById(R.id.register_fragment_ET_Password);
        registerFragmentETPhone = view.findViewById(R.id.register_fragment_ET_Phone);
        registerFragmentETConfirmPassword = view.findViewById(R.id.register_fragment_ET__confirm_password);
        registerFragmentETHistory = view.findViewById(R.id.register_fragment_ET_history);
        Button registerFragmentBtnLogin = view.findViewById(R.id.register_fragment_btn_login);


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

        registerFragmentBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registerFragmentETName.getText().toString();
                String email = registerFragmentETEmail.getText().toString();
                String birthDate = registerFragmentETBirthDate.getText().toString();
                String phone = registerFragmentETPhone.getText().toString();
                String password = registerFragmentETPassword.getText().toString();
                String history = registerFragmentETName.getText().toString();
                String ConfirmPassword = registerFragmentETConfirmPassword.getText().toString();
                String city = registerFragmentETCity.getSelectedItem().toString();
                String Capital = registerFragmentETCapital.getSelectedItem().toString();
                String BloodTypes = registerFragmentETBloodTypes.getSelectedItem().toString();


                register(new Client(name, email, birthDate, city, phone, history, BloodTypes, password, ConfirmPassword,Capital));

                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                        R.id.user_cycle_activity, new LoginFragment());


            }
        });


    }


    private void getGovernorates() {
        apiService.getGovernorates().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(@NotNull Call<Cities> call, @NotNull Response<Cities> response) {
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    List<String> listSpinner = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        listSpinner.add(response.body().getData().get(i).getName());
                    }
                    ArrayAdapter<String> adapterGovernorate = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapterGovernorate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    registerFragmentETCity.setAdapter(adapterGovernorate);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Cities> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getCities() {

        governoratesId = (int) registerFragmentETCity.getSelectedItemId() + 1;
        registerFragmentETCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiService.getCities(position + 1).enqueue(new Callback<Cities>() {
                    @Override
                    public void onResponse(@NotNull Call<Cities> call, @NotNull Response<Cities> response) {
                        List<String> listCitySpinner = new ArrayList<>();
                        assert response.body() != null;
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            listCitySpinner.add(response.body().getData().get(i).getName());
                        }
                        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                                android.R.layout.simple_spinner_item, listCitySpinner);
                        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        registerFragmentETCapital.setAdapter(adapterCity);
                    }

                    @Override
                    public void onFailure(@NotNull Call<Cities> call, @NotNull Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getBloodTypes() {
        apiService.getBloodType().enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(@NotNull Call<Cities> call, @NotNull Response<Cities> response) {
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    List<String> listBloodType = new ArrayList<>();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        listBloodType.add(response.body().getData().get(i).getName());
                    }
                    ArrayAdapter<String> adapterBloodType = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
                            android.R.layout.simple_spinner_item, listBloodType);
                    adapterBloodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    registerFragmentETBloodTypes.setAdapter(adapterBloodType);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Cities> call, @NotNull Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(Client registerClient) {
        apiService.registerClient(registerClient).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(@NotNull Call<Client> call, @NotNull Response<Client> response) {
                assert response.body() != null;
                if (response.body().getStatus() == 1) {
                    Toast.makeText(getActivity(), "registerClient", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Client> call, @NotNull Throwable t) {

            }
        });
    }


    @Override
    public void onBack() {
        HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), R.id.user_cycle_activity, new LoginFragment());
    }


}












