package com.goutam.naviewfragment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goutam.naviewfragment.R;

/**
 * Created by Goutam Kumar K. on 22/2/17.
 */

public class SecondaryFragment extends Fragment {
    private View view = null;

    public static SecondaryFragment getInstance(){
        return  new SecondaryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_secondary,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
