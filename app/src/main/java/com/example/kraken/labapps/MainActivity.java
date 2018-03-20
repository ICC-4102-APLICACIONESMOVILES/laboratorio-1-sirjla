package com.example.kraken.labapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kraken.labapps.CredentialManager;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private String email;
    private String password;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(
                "com.example.myapp.PREFERENCE_FILE_KEY",
                Context.MODE_PRIVATE

        );

        String savedEmail = sharedPref.getString("email", "none");
        String savedPassword = sharedPref.getString("password", "");

        if (!Objects.equals(savedEmail, "none")){
            email = savedEmail;
            password = savedPassword;
            modifyLayout();
        }
    }

    public void loginButtonCLick(View v){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivityForResult(intent, 1);
    }

    public void logoutButtonClick(View v){
        Context context = getApplicationContext();
        CredentialManager.clearLogin(context);
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        MainActivity.this.startActivity(intent);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1) {
            if(resultCode == MainActivity.RESULT_OK){
                email = data.getStringExtra("email");
                password = data.getStringExtra("password");
                CredentialManager.saveLogin(email, password, getApplicationContext());
                modifyLayout();
            }
            if (resultCode == MainActivity.RESULT_CANCELED) {
                //Do nothing for now
            }
        }
    }//onActivityResult

    private void modifyLayout(){
        Button loginButton = findViewById(R.id.login);
        loginButton.setVisibility(View.GONE);

        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setVisibility(View.VISIBLE);

        TextView emailText = findViewById(R.id.email);
        TextView passwordText = findViewById(R.id.password);

        emailText.setVisibility(View.VISIBLE);
        emailText.setText(email);

        passwordText.setVisibility(View.VISIBLE);
        passwordText.setText(password);
    }

}
