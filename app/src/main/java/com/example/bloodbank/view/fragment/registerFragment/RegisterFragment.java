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
import com.example.bloodbank.network.api.APIClient;
import com.example.bloodbank.network.models.DateTxt;
import com.example.bloodbank.network.models.generalResponse.GeneralResponse;
import com.example.bloodbank.network.models.login.Auth;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;
import com.example.bloodbank.view.fragment.BaseFragment;
import com.example.bloodbank.view.fragment.loginFragment.LoginFragment;

import org.jetbrains.annotations.NotNull;

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
    private SpinnerAdapter spinnerAdapterGovernorates;


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
//        getBloodTypes();
//        getGovernorates();
//        getCities();
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
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),
                        R.id.user_cycle_activity, new LoginFragment());
            }
        });


    }


//        apiService.getGovernorates().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
//                assert response.body() != null;
//                if (response.body().getStatus() == 1) {
//                    List<String> listSpinner = new ArrayList<>();
//                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        listSpinner.add(response.body().getData().get(i).getName());
//                    }
//                    ArrayAdapter<String> adapterGovernorate = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
//                            android.R.layout.simple_spinner_item, listSpinner);
//                    adapterGovernorate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    registerFragmentETCity.setAdapter(adapterGovernorate);
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<GeneralResponse> call, @NotNull Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        apiService.getGovernorates().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
//                assert response.body() != null;
//                if(response.body().getStatus()==1) {
//                    sliderAdapterGovernorates = new SliderAdapter(Objects.requireNonNull(getActivity()));
//
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<GeneralResponse> call, @NotNull Throwable t) {
//
//            }
//        });


   private void getSpinnerData(Call<GeneralResponse> call, Spinner spinner, SpinnerAdapter adapter,Integer selectItem,String hint)
   {
       call.enqueue(new Callback<GeneralResponse>() {
           @Override
           public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
               try {
                   assert response.body() != null;
                   spinnerAdapterGovernorates=new SpinnerAdapter(getActivity());
                   adapter.setData(response.body().getData(),hint);
                   spinner.setAdapter(adapter);
                   for (int i = 0; i <adapter.generalResponseDataList.size() ; i++) {
                       if (adapter.generalResponseDataList.get(i).getId()==selectItem) {
                           spinner.setSelection(i);
                           break;
                       }
                   }

               }catch (Exception e)
               {

               }
           }

           @Override
           public void onFailure(Call<GeneralResponse> call, Throwable t) {

           }
       });
   }

//    private void getCities() {
//
//        governoratesId = (int) registerFragmentETCity.getSelectedItemId() + 1;
//        registerFragmentETCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                apiService.getCities(position + 1).enqueue(new Callback<GeneralResponse>() {
//                    @Override
//                    public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
//                        List<String> listCitySpinner = new ArrayList<>();
////                        assert response.body() != null;
////                        for (int i = 0; i < response.body().getData().size(); i++) {
////                            listCitySpinner.add(response.body().getData().get(i).getName());
////                        }
//                        ArrayAdapter<String> adapterCity = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
//                                android.R.layout.simple_spinner_item, listCitySpinner);
//                        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                        registerFragmentETCapital.setAdapter(adapterCity);
//                    }
//
//                    @Override
//                    public void onFailure(@NotNull Call<GeneralResponse> call, @NotNull Throwable t) {
//                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//    }
//
//    private void getBloodTypes() {
//        apiService.getBloodType().enqueue(new Callback<GeneralResponse>() {
//            @Override
//            public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
//                assert response.body() != null;
//                if (response.body().getStatus() == 1) {
//                    List<String> listBloodType = new ArrayList<>();
//                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        listBloodType.add(response.body().getData().get(i).getName());
//                    }
//                    ArrayAdapter<String> adapterBloodType = new ArrayAdapter<>(Objects.requireNonNull(getContext()),
//                            android.R.layout.simple_spinner_item, listBloodType);
//                    adapterBloodType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    registerFragmentETBloodTypes.setAdapter(adapterBloodType);
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<GeneralResponse> call, @NotNull Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
    private void registerClient() {
        String name = registerFragmentETName.getText().toString();
        String email = registerFragmentETEmail.getText().toString();
        String birthDate = registerFragmentETBirthDate.getText().toString();
        String phone = registerFragmentETPhone.getText().toString();
        String password = registerFragmentETPassword.getText().toString();
        String history = registerFragmentETName.getText().toString();
        String confirmPassword = registerFragmentETConfirmPassword.getText().toString();
        String city = registerFragmentETCity.getSelectedItem().toString();
        String BloodTypes = registerFragmentETBloodTypes.getSelectedItem().toString();

        apiService.register(name,email,birthDate,city,phone,history,password,confirmPassword,BloodTypes).enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NotNull Call<Auth> call, @NotNull Response<Auth> response) {

                assert response.body() != null;
                if(response.body().getStatus()==1)
                {
                    getSpinnerData((Call<GeneralResponse>) response.body().getData().getClient().getCity().getGovernorate(),registerFragmentETCity,spinnerAdapterGovernorates,
                            response.body().getData().getClient().getCity().getId(),"اختر المحافظه");
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












