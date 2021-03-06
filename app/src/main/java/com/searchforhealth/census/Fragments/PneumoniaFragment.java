package com.searchforhealth.census.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.searchforhealth.census.R;

public class PneumoniaFragment extends BaseFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pneumonia_fragment, container, false);
        return  root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.getSupportActionBar().setTitle("Pneumonia Information");
    }
}
