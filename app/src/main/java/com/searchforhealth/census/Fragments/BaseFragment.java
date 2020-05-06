package com.searchforhealth.census.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.searchforhealth.census.MainActivity;


/**
 * Created by Dvimay on 6/30/2016.
 */
public class BaseFragment extends Fragment {
    public MainActivity mActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (MainActivity) this.getActivity();

        System.out.println("BAseFragment OnCreate");

    }

    public boolean onBackPressed(){
        return false;
    }

}
