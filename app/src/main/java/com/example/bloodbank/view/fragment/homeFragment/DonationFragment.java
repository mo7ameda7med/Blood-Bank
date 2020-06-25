package com.example.bloodbank.view.fragment.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.DonationAdapter;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.network.models.donation.Donation;
import com.example.bloodbank.network.models.donation.DonationData;
import com.example.bloodbank.util.General;
import com.example.bloodbank.util.OnEndLess;
import com.example.bloodbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;
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
public class DonationFragment extends BaseFragment {

    @BindView(R.id.fragment_donation_ET_Governorates)
    Spinner fragmentDonationETGovernorates;
    @BindView(R.id.fragment_donation_ET_blood_spinner)
    Spinner fragmentDonationETBloodSpinner;
    @BindView(R.id.fragment_donation_RV_donation)
    RecyclerView fragmentDonationRVDonation;
    @BindView(R.id.fragment_donation_I_btn_filter)
    ImageButton fragmentDonationIBtnFilter;
    private SpinnerAdapter GovernoratesAdapter, bloodTypesAdapter;
    private OnEndLess onEndLess;
    private DonationAdapter donationAdapter;
    private List<DonationData> donation = new ArrayList<>();
    private int maxPage = 0;
    private boolean filter = false;


    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initFragment();
        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        ButterKnife.bind(this, view);
        bloodTypesAdapter = new SpinnerAdapter(getActivity());
        General.getSpinnerData(getActivity(), fragmentDonationETBloodSpinner, bloodTypesAdapter, getString(R.string.اختر_فصيله_الدم), getClient().getBloodType());

        GovernoratesAdapter = new SpinnerAdapter(getActivity());
        General.getSpinnerData(getActivity(), fragmentDonationETGovernorates, GovernoratesAdapter, getString(R.string.Wait), getClient().getGovernorates());


        setupRv();
        getDonation(1,getClient().getDonation("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",1));
        return view;
    }

    private void setupRv() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Objects.requireNonNull(getActivity())
                .getApplicationContext(),
                LinearLayoutManager.VERTICAL
                , false);
        fragmentDonationRVDonation.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        if (filter) {
                            onFilter(current_page);
                        } else {
                            getDonation(current_page);
                        }

                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };
        fragmentDonationRVDonation.addOnScrollListener(onEndLess);
        donationAdapter = new DonationAdapter( getActivity(), donation);
        fragmentDonationRVDonation.setAdapter(donationAdapter);
    }

    private void onFilter(int page) {
        filter = true;
        onEndLess.current_page = 1;
        onEndLess.previous_page = 1;
        onEndLess.totalItemCount = 0;
        maxPage = 0;
        donation = new ArrayList<>();
        donationAdapter = new DonationAdapter( getActivity(), donation);
        fragmentDonationRVDonation.setAdapter(donationAdapter);

        Call<Donation> call = getClient().getDonationFilter("mg1i1XHW5bHMJzjxi6ymJbVOflHiaCH5v8cYjS1aOaMphzubY4DtOsyrtIUf", 1
                , GovernoratesAdapter.selectedId, bloodTypesAdapter.selectedId);
        getDonation(page, call);
    }

    private void getDonation(int page) {
        Call<Donation> call = getClient().getDonation("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", 1);
        getDonation(page, call);
    }

    private void getDonation(int page, Call<Donation> call) {

        call.enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                assert response.body() != null;
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        donation.addAll(response.body().getData().getData());
                        donationAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "s", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.fragment_donation_I_btn_filter)
    public void onViewClicked() {
        onFilter(1);
    }
}
