package com.example.bloodbank.view.fragment;





import androidx.fragment.app.Fragment;

import com.example.bloodbank.view.activity.BaseActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {
    public BaseActivity baseActivity;

    public void initFragment ()
    {
        baseActivity= (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack()
    {
        baseActivity.superOnBackPressed();
    }


}
