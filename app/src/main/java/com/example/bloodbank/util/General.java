package com.example.bloodbank.util;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.network.models.generalResponse.GeneralResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class General {

    public static void getSpinnerData(Activity activity, Spinner spinner, SpinnerAdapter adapter, String hint, Call<GeneralResponse> method) {
        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(@NotNull Call<GeneralResponse> call, @NotNull Response<GeneralResponse> response) {
                try {
                    assert response.body() != null;
                    if (response.body().getStatus() == 1) {
                        adapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(adapter);

                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void onFailure(@NotNull Call<GeneralResponse> call, @NotNull Throwable t) {

            }
        });
    }

    public static void getSpinnerData(Activity activity, Spinner spinner, SpinnerAdapter adapter, String hint,
                                      Call<GeneralResponse> method, Spinner spinner2, SpinnerAdapter adapter2, String hint2,
                                      Call<GeneralResponse> method2) {
        method.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
               try {
                   if (response.body().getStatus() == 1) {
                       adapter.setData(response.body().getData(), hint);
                       spinner.setAdapter(adapter);

                       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                               if (position!=0) {
                                   getSpinnerData(activity,spinner2,adapter2,hint2,method2);
                               }
                           }
                           @Override
                           public void onNothingSelected(AdapterView<?> parent) {

                           }
                       });
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
}
//
//     HelperMethod.onLoadImageFromUrl(itemPostIVPostImage,postsFull.get(position).getData().getData().get(0).getThumbnailFullPath(),context);
//             itemPostIBFavorite.setOnClickListener(new View.OnClickListener() {
//
//@Override
//public void onClick(View v) {
//        boolean isFavourite = readState();
//
//        if (isFavourite) {
//        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
//        isFavourite = false;
//        saveState(activity,isFavourite);
//
//        } else {
//        itemPostIBFavorite.setBackgroundResource(R.drawable.ic_favorite_border_24dp);
//        isFavourite = true;
//        saveState(activity,isFavourite);
//
//        }
//
//        }
//        });
//
//        }

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