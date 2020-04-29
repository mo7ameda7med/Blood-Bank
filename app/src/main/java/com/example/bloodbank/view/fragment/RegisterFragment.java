package com.example.bloodbank.view.fragment;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.bloodbank.network.models.login.Login;
import com.example.bloodbank.network.services.ApiService;
import com.example.bloodbank.util.HelperMethod;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment {


      int governoratesId=1;
      int cityid=1;
      int BloodTypesId=1;
      EditText registerFragmentETName;
      EditText registerFragmentETEmail;
      TextView registerFragmentETBirthDate;
      EditText registerFragmentETHistory;
      EditText registerFragmentETPhone;
      EditText registerFragmentETPassword;
      EditText registerFragmentETConfirmPassword;
      Button registerFragmentBtnLogin;
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
        View view =inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        getBloodTypes();
        getGovernorates();
        getCities();
        return view;
    }

    private void initView(View view) {
        apiService= APIClient.getClient().create(ApiService.class);
        registerFragmentETCity=view.findViewById(R.id.register_fragment_ET_city);
        registerFragmentETCapital=view.findViewById(R.id.register_fragment_ET_capital);
        registerFragmentETBloodTypes=view.findViewById(R.id.register_fragment_ET_blood_spinner);
        registerFragmentETBirthDate=view.findViewById(R.id.register_fragment_ET_birth_date);
        registerFragmentETName=view.findViewById(R.id.register_fragment_ET_name);
        registerFragmentETEmail=view.findViewById(R.id.register_fragment_ET_email);
        registerFragmentETPassword=view.findViewById(R.id.register_fragment_ET_Password);
        registerFragmentETPhone=view.findViewById(R.id.register_fragment_ET_Phone);
        registerFragmentETConfirmPassword=view.findViewById(R.id.register_fragment_ET__confirm_password);
        registerFragmentETHistory=view.findViewById(R.id.register_fragment_ET_history);
        registerFragmentBtnLogin=view.findViewById(R.id.register_fragment_btn_login);




        registerFragmentETBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title="time";
                DateTxt dateTxt=new DateTxt("01","02","2017","4");
                HelperMethod.showCalender(getContext(),title,registerFragmentETBirthDate,dateTxt);
            }
        });
        
        registerFragmentBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=registerFragmentETName.getText().toString();
                String email=registerFragmentETEmail.getText().toString();
                String birthDate=registerFragmentETBirthDate.getText().toString();
                String phone=registerFragmentETPhone.getText().toString();
                String password=registerFragmentETPassword.getText().toString();
                String history=registerFragmentETName.getText().toString();
                String ConfirmPassword=registerFragmentETConfirmPassword.getText().toString();

                 register(new Client(name,email,birthDate,phone,history,password,ConfirmPassword));


            }
        });


    }


    private void getGovernorates()
    {
       apiService.getGovernorates().enqueue(new Callback<Cities>() {
           @Override
           public void onResponse(@NotNull Call<Cities> call, @NotNull Response<Cities> response) {
               assert response.body() != null;
               if(response.body().getStatus()==1)
               {
                   List<String> listSpinner = new ArrayList<>();
                   for (int i = 0; i < response.body().getData().size(); i++){
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

        governoratesId=(int)registerFragmentETCity.getSelectedItemId()+1;
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

   private void getBloodTypes()
   {
       apiService.getBloodType().enqueue(new Callback<Cities>() {
           @Override
           public void onResponse(@NotNull Call<Cities> call, @NotNull Response<Cities> response) {
                   assert response.body() != null;
                   if(response.body().getStatus()==1)
                   {
                       List<String> listBloodType = new ArrayList<>();
                       for (int i = 0; i < response.body().getData().size(); i++){
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

   private void register(Client registerClient)
   {
       apiService.registerClient(registerClient).enqueue(new Callback<Login>() {
           @Override
           public void onResponse(@NotNull Call<Login> call, @NotNull Response<Login> response) {
               if(response.body().getStatus()==1)
               {
                   Toast.makeText(getActivity(), "registerClient", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(@NotNull Call<Login> call, @NotNull Throwable t) {

           }
       });
   }

   



            @Override
            public void onBack() {
                HelperMethod.replaceFragment(Objects.requireNonNull(getActivity()).getSupportFragmentManager(),R.id.user_cycle_activity,new LoginFragment());
            }


}












