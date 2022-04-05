package com.customerglu.customerglu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.customerglu.sdk.CustomerGlu;


public class SplashScreen extends AppCompatActivity {

    private String AuthKey;
    CustomerGlu customerGlu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        customerGlu =  CustomerGlu.getInstance();
        Context context = getApplicationContext();
      //  customerGlu.enablePreaching(getApplicationContext());
        AuthKey = Prefs.getKey(context, "cus_token");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Start your app main activity

                // Start your app main activity
                if (AuthKey.equals("") || AuthKey.isEmpty()) {
                    // Start your app main activity

                    Intent i = new Intent(SplashScreen.this, UserForm.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();

                }
                else {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }

            }
        }, 2900);


    }
}
