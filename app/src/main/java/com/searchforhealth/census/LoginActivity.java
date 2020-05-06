package com.searchforhealth.census;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.searchforhealth.census.Network.AlertClass;
import com.searchforhealth.census.Network.NetworkStatus;
import com.searchforhealth.census.Preference.Constant;
import com.searchforhealth.census.Preference.SharedCS;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView input_email, input_password, input_ForgotPswd;
    String email,password;
    AlertClass alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        SharedCS scs = new SharedCS(this);

        btnLogin= (Button) findViewById(R.id.btn_login);

        input_email = (TextView) findViewById(R.id.input_email);
        input_password = (TextView) findViewById(R.id.input_password);

        input_ForgotPswd = (TextView) findViewById(R.id.txtForgotPswd);

        alert = new AlertClass();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_email.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, getString(R.string.err_msg_email), Toast.LENGTH_SHORT).show();
                    requestFocus(input_email);
                    return;
                }

                if (!Constant.validatePassword(input_password.getText().toString())) {
                    Toast.makeText(LoginActivity.this, getString(R.string.err_msg_password), Toast.LENGTH_SHORT).show();
                    requestFocus(input_password);
                    return;
                }

                email = input_email.getText().toString();
                password = input_password.getText().toString();

                if(email.equals("admin@search.com") && password.equals("qwerty")){
                    SharedCS.writeString(SharedCS.LOGIN_STATUS, "Y");
                    SharedCS.writeString(SharedCS.EMAIL, email);
                    finish();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    alert.customDialogforAlertMessage(LoginActivity.this, "", "Login Failed. Please Contact Admin");
                }


//                String network_status= NetworkStatus.checkConnection(getActivity());
//                if (network_status.equals("false")) {
//                    alert.customDialogforAlertMessage(this, "No internet connection", "Please check your internet connection");
//                }else {
//                    WebService service = new WebService(callback);
//                    List<NameValuePair> params = new ArrayList<NameValuePair>();
//
//                    params.add(new BasicNameValuePair("email", input_email.getText().toString()));
//                    params.add(new BasicNameValuePair("password", input_password.getText().toString()));
//                    params.add(new BasicNameValuePair("device_id",  Constant.registrationId));//Registration ID
//                    params.add(new BasicNameValuePair("os_type", "1"));
//
//                    service.getService(getActivity(), Constant.login, params);
//                }
            }
        });

        input_ForgotPswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Forgot password clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


//    WebService.Callback callback= new WebService.Callback() {
//
//        @Override
//        public void onSuccess(int reqestcode, JSONObject rootjson) {
//            // TODO Auto-generated method stub
//            System.out.println("Login::: Success " + rootjson.toString());
//            try {
//                JSONObject Result = rootjson.getJSONObject("Result");
//                String success = Result.getString("ErrorCode");
//                System.out.println("Login::: Success");
//
////                0=>'success',1=>'email and password are mandatory',2=>'invalid email/username','3'=>'invalid password'
//                if (success.equalsIgnoreCase("0")){
//                    SharedCS.writeString(SharedCS.LOGIN_STATUS, "Y");
//                    SharedCS.writeString(SharedCS.EMAIL, email);
//                    JSONObject result = Result.getJSONObject("result");
//                    String userId = result.getString("pk_patient_id");
//                    SharedCS.writeString(SharedCS.USER_ID, userId);
//
//                    Intent intent = new Intent(getActivity(), CloudSihatMainActivity.class);
//                    startActivity(intent);
//                    getActivity().finish();
//                }else if (success.equalsIgnoreCase("1")){
//                    System.out.println("Login::: email and password are mandatory ");
//                    alert.customDialogforAlertMessage(getActivity(), "", "Email and Password are Mandatory.");
//                }else if (success.equalsIgnoreCase("2")){
//                    System.out.println("Login::: invalid email/username");
//                    alert.customDialogforAlertMessage(getActivity(), "", "Invalid Username / Email Id");
//                }else if (success.equalsIgnoreCase("3")){
//                    System.out.println("Login::: invalid password");
//                    alert.customDialogforAlertMessage(getActivity(), "", "Invalid Password");
//                }else{
//                    System.out.println("Login::: Failed");
//                    alert.customDialogforAlertMessage(getActivity(), "", "Login Failed. Please Contact Admin");
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                System.out.println("Login::: Catch");
//                alert.customDialogforAlertMessage(getActivity(), "Error", "Please Contact Admin");
//            }
//
//
//        }
//
//
//        @Override
//        public void onError(int reqestcode, String error) {
//            // TODO Auto-generated method stub
////            try{
////                AlertClass alert = new AlertClass();
////                alert.customDialogforAlertMessage(getActivity(), "Error", error);
////            }catch(Exception e)
////            {
//            System.out.println("Login::: Error" + error);
////            }
//        }
//    };
}
