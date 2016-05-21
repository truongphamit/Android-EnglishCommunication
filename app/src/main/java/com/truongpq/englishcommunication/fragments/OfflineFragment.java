package com.truongpq.englishcommunication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truongpq.englishcommunication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfflineFragment extends Fragment {


    public static OfflineFragment newInstance() {
        OfflineFragment fragment = new OfflineFragment();
        return fragment;
    }

    public OfflineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline, container, false);
    }

}
