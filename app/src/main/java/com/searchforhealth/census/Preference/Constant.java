package com.searchforhealth.census.Preference;

import android.text.TextUtils;

/**
 * Created by Dvimay on 6/28/2016.
 */
public class Constant {

    public static String baseURL = "http://studypeek.com/test/";

    public static String login = baseURL + "login";
    public static String apiGet = "webapi.php?userId=1";
    public static String apiPost = "webapi.php";

    ///Methods
    public static boolean validateEmail(String input_email) {
        String email = input_email.trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String input_password) {
        if (input_password.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validatePersonName(String name) {
        if(TextUtils.isEmpty(name) || !name.matches("^[\\p{L} .'-]+$")){
            return false;
        }else {
            return true;
        }
    }
}
