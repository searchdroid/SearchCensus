package com.searchforhealth.census.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.searchforhealth.census.R;

public class FamilyHeadFragments extends BaseFragment {
    Button btnNext;
    Bundle bundle;
    String callfrom = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.familyhead_fragment, container, false);
        btnNext = (Button) root.findViewById(R.id.btnNext);
        bundle = getArguments();
        if(bundle != null){
            callfrom = bundle.getString("callfrom");
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new FamilyMembersFragment(), false, true, bundle, "Family Members");
            }
        });
        return  root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.getSupportActionBar().setTitle("Family Heads");
    }
}
