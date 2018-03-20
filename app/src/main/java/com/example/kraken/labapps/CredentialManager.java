package com.example.kraken.labapps;


import android.content.Context;
import android.content.SharedPreferences;


public class CredentialManager {
    public static void saveLogin(String email, String password, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(
                "com.example.myapp.PREFERENCE_FILE_KEY",
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public static void clearLogin(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(
                "com.example.myapp.PREFERENCE_FILE_KEY",
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.remove("email");
        editor.remove("password");
        editor.apply();
    }
}
