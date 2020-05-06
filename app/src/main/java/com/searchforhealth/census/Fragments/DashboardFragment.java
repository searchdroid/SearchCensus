package com.searchforhealth.census.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.searchforhealth.census.MainActivity;
import com.searchforhealth.census.R;

public class DashboardFragment extends BaseFragment {

    public MainActivity mActivity;
    private TextView txtBirtSlip, txtDeathSlip, txtPneumonia, txtMedicine, txtHealthEducation, txtSupervisorTask, txtMonthlyReport, txtNotification, txtVillageInfo;
    Bundle bundle;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dashboard_fragment, container, false);
        mActivity = (MainActivity) this.getActivity();

        txtBirtSlip = root.findViewById(R.id.txtBirtSlip);
        txtDeathSlip = root.findViewById(R.id.txtDeathSlip);
        txtPneumonia = root.findViewById(R.id.txtPneumonia);
        txtMedicine = root.findViewById(R.id.txtMedicine);
        txtHealthEducation = root.findViewById(R.id.txtHealthEducation);
        txtSupervisorTask = root.findViewById(R.id.txtSupervisorTask);
        txtMonthlyReport = root.findViewById(R.id.txtMonthlyReport);
        txtNotification = root.findViewById(R.id.txtNotification);
        txtVillageInfo = root.findViewById(R.id.txtVillageInfo);

        txtBirtSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("callfrom", "birthslip");
                mActivity.pushFragments(new FamilyHeadFragments(), false, true, bundle, "Family Heads");
            }
        });

        txtDeathSlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("callfrom", "deathslip");
                mActivity.pushFragments(new FamilyHeadFragments(), false, true, bundle, "Family Heads");
            }
        });

        txtPneumonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("callfrom", "pneumonia");
                mActivity.pushFragments(new FamilyHeadFragments(), false, true, bundle, "Family Heads");
            }
        });

        txtMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString("callfrom", "medicine");
                mActivity.pushFragments(new FamilyHeadFragments(), false, true, bundle, "Family Heads");
            }
        });

        txtHealthEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new HealthEducationFragment(), false, true, null, "Health Education");
            }
        });

        txtSupervisorTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new SupervisorTaskFragment(), false, true, null, "Supervisor Task");
            }
        });

        txtMonthlyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new MonthlyReportFragment(), false, true, null, "Monthly Report");
            }
        });

        txtNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new ReminderFragment(), false, true, null, "Reminder");
            }
        });

        txtVillageInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.pushFragments(new VillageInfoFragment(), false, true, null, "Village Information");
            }
        });

        return root;

    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.getSupportActionBar().setTitle("Dashboard");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
