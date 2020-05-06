package com.searchforhealth.census.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.searchforhealth.census.Network.AlertClass;
import com.searchforhealth.census.Network.NetworkStatus;
import com.searchforhealth.census.Preference.Constant;
import com.searchforhealth.census.R;
import com.searchforhealth.census.RestApis.FetchDataListener;
import com.searchforhealth.census.RestApis.GETAPIRequest;
import com.searchforhealth.census.RestApis.POSTAPIRequest;
import com.searchforhealth.census.RestApis.RequestQueueService;

import org.json.JSONException;
import org.json.JSONObject;

public class DeathSlipFragment extends BaseFragment {

    Bundle bundle;
    Button btnGetApiCall, btnPostApiCall;
    AlertClass alert;
    private TextView txtResult;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.deathslip_fragment, container, false);
        init(root);
        return  root;
    }

    private void init(View v){
        bundle = getArguments();
        btnGetApiCall = (Button) v.findViewById(R.id.btnGetApiCall);
        btnPostApiCall  = (Button) v.findViewById(R.id.btnPostApiCall);
        txtResult = (TextView) v.findViewById(R.id.txtResult);
        alert = new AlertClass();
        if(bundle != null){
            String calledFrom = bundle.getString("calledFrom");
            System.out.println("calledFrom :" +calledFrom);
        }

        btnGetApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String network_status = NetworkStatus.checkConnection(getActivity());
                    if (network_status.equals("false")) {
                        alert.customDialogforAlertMessage(getActivity(), "No internet connection", "Please check your internet connection");
                    }else {
                        //Create Instance of GETAPIRequest and call it's
                        //request() method
                        GETAPIRequest getapiRequest = new GETAPIRequest();
                        //Attaching only part of URL as base URL is given
                        //in our GETAPIRequest(of course that need to be same for all case)
                        getapiRequest.request(getActivity(),fetchGetResultListener, Constant.apiGet);
                        Toast.makeText(getActivity(),"GET API called",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnPostApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String network_status = NetworkStatus.checkConnection(getActivity());
                    if (network_status.equals("false")) {
                        alert.customDialogforAlertMessage(getActivity(), "No internet connection", "Please check your internet connection");
                    }else {
                        //Create Instance of POSTAPIRequest and call it's
                        //request() method
                        POSTAPIRequest postapiRequest = new POSTAPIRequest();
                        //Attaching only part of URL as base URL is given
                        //in our POSTAPIRequest(of course that need to be same for all case)
                        JSONObject params=new JSONObject();
                        try {
                            //Creating POST body in JSON format
                            //to send in POST request
                            params.put("userId",2);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        postapiRequest.request(getActivity(), fetchPostResultListener, params, Constant.apiPost);
                        Toast.makeText(getActivity(),"POST API called",Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    //Implementing interfaces of FetchDataListener for GET api request
    FetchDataListener fetchGetResultListener=new FetchDataListener() {
        @Override
        public void onFetchComplete(JSONObject data) {
            //Fetch Complete. Now stop progress bar  or loader
            //you started in onFetchStart
            RequestQueueService.cancelProgressDialog();
            try {
                //Now check result sent by our GETAPIRequest class
                if (data != null) {
                    if (data.has("success")) {
                        int success = data.getInt("success");
                        if(success==1){
                            JSONObject response=data.getJSONObject("response");
                            if(response!=null) {
                                //Display the result
                                //Or, You can do whatever you need to
                                //do with the JSONObject
                                txtResult.setText(response.toString(4));
                            }
                        }else{
                            RequestQueueService.showAlert("Error! No data fetched", getActivity());
                        }
                    }
                } else {
                    RequestQueueService.showAlert("Error! No data fetched", getActivity());
                }
            }catch (Exception e){
                RequestQueueService.showAlert("Something went wrong", getActivity());
                e.printStackTrace();
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            //Show if any error message is there called from GETAPIRequest class
            RequestQueueService.showAlert(msg,getActivity());
        }

        @Override
        public void onFetchStart() {
            //Start showing progressbar or any loader you have
            RequestQueueService.showProgressDialog(getActivity());
        }
    };

    //Implementing interfaces of FetchDataListener for POST api request
    FetchDataListener fetchPostResultListener=new FetchDataListener() {
        @Override
        public void onFetchComplete(JSONObject data) {
            //Fetch Complete. Now stop progress bar  or loader
            //you started in onFetchStart
            RequestQueueService.cancelProgressDialog();
            try {
                //Now check result sent by our POSTAPIRequest class
                if (data != null) {
                    if (data.has("success")) {
                        int success = data.getInt("success");
                        if(success==1){
                            JSONObject response=data.getJSONObject("response");
                            if(response!=null) {
                                //Display the result
                                //Or, You can do whatever you need to
                                //do with the JSONObject
                                txtResult.setText(response.toString(4));
                            }
                        }else{
                            RequestQueueService.showAlert("Error! No data fetched", getActivity());
                        }
                    }
                } else {
                    RequestQueueService.showAlert("Error! No data fetched", getActivity());
                }
            }catch (Exception e){
                RequestQueueService.showAlert("Something went wrong", getActivity());
                e.printStackTrace();
            }
        }

        @Override
        public void onFetchFailure(String msg) {
            RequestQueueService.cancelProgressDialog();
            //Show if any error message is there called from POSTAPIRequest class
            RequestQueueService.showAlert(msg,getActivity());
        }

        @Override
        public void onFetchStart() {
            //Start showing progressbar or any loader you have
            RequestQueueService.showProgressDialog(getActivity());
        }
    };


    @Override
    public void onResume() {
        super.onResume();
        mActivity.getSupportActionBar().setTitle("Death Slip");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
