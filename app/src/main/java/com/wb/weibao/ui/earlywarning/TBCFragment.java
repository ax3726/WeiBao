package com.wb.weibao.ui.earlywarning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wb.weibao.R;
import com.wb.weibao.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class TBCFragment extends Fragment {
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.from(getActivity()).inflate(R.layout.fragment_tbc, container, false);
        
        return rootView;

    }
}
