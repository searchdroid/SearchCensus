package com.searchforhealth.census.Fragments;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.searchforhealth.census.Database.DatabaseHelper;
import com.searchforhealth.census.MainActivity;
import com.searchforhealth.census.Model.Model_UserNames;
import com.searchforhealth.census.Preference.ConstantDatabase;
import com.searchforhealth.census.R;

public class BirthSlipFragment extends BaseFragment {

    Button btnAdd, btnGet;
    EditText edtFName, edtLName;
    public MainActivity mActivity;
    DatabaseHelper db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.birthslip_fragment, container, false);

        btnAdd = root.findViewById(R.id.btnAdd);
        btnGet = root.findViewById(R.id.btnGet);
        edtFName = root.findViewById(R.id.edtFName);
        edtLName = root.findViewById(R.id.edtLName);
        mActivity = (MainActivity) this.getActivity();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = new DatabaseHelper(getActivity());
                Model_UserNames userName = new Model_UserNames();
                userName.setFirstName(edtFName.getText().toString());
                userName.setLastname(edtLName.getText().toString());

                long record = db.insertUserNamesData(userName);
                System.out.println("Inserted Record " + record);

                if (db != null)
                    db.close();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllRecords();
                //getRecordByCondition();
            }
        });
        return root;
    }

    private void getRecordByCondition() {
        db = new DatabaseHelper(getActivity());
        Cursor cursor;
        String fName = "Arun";
        try{
            cursor = db.selectUsersDataByFirstName(fName);
            System.out.println("++++++++cursor count :" + cursor.getCount());

            /* Check if our result was valid. */
            if (cursor.getCount() != 0) {

                if (!cursor.isAfterLast()) {

                    cursor.moveToFirst(); // it's very important to do this action
                    // otherwise your Cursor object did not
                    // get work

                    /* Check if at least one Result was returned. */
                    while (!cursor.isAfterLast()) {

                        int user_fnameIndex = cursor.getColumnIndex(ConstantDatabase.COLUMN_FIRSTNAME);
                        String firstName = cursor.getString(user_fnameIndex);

                        int user_lnameIndex = cursor.getColumnIndex(ConstantDatabase.COLUMN_LASTNAME);
                        String lastName = cursor.getString(user_lnameIndex);

                        System.out.println("$$ User $$" + firstName + "\t" + lastName);

                        cursor.moveToNext();
                    }
                }

            } else {
                System.out.println("Cursor is empty");
            }

        }catch(Exception e){
            System.out.println("Cursor Exc"+e.toString());
        }
    }

    private void getAllRecords() {

        db = new DatabaseHelper(getActivity());
        Cursor cursor;
        try{
            cursor = db.selectAllUsersDataData();
            System.out.println("++++++++cursor count :" + cursor.getCount());

            /* Check if our result was valid. */
            if (cursor.getCount() != 0) {

                if (!cursor.isAfterLast()) {

                    cursor.moveToFirst(); // it's very important to do this action
                    // otherwise your Cursor object did not
                    // get work

                    /* Check if at least one Result was returned. */
                    while (!cursor.isAfterLast()) {

                        int user_fnameIndex = cursor.getColumnIndex(ConstantDatabase.COLUMN_FIRSTNAME);
                        String firstName = cursor.getString(user_fnameIndex);

                        int user_lnameIndex = cursor.getColumnIndex(ConstantDatabase.COLUMN_LASTNAME);
                        String lastName = cursor.getString(user_lnameIndex);

                        System.out.println("$$ User $$" + firstName + "\t" + lastName);

                        cursor.moveToNext();
                    }
                }

            } else {
                System.out.println("Cursor is empty");
            }

        }catch(Exception e){
            System.out.println("Cursor Exc"+e.toString());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mActivity.getSupportActionBar().setTitle("Birth Slip");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
