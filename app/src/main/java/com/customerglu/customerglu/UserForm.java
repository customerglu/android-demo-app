package com.customerglu.customerglu;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.customerglu.sdk.CustomerGlu;
import com.customerglu.sdk.Interface.DataListner;
import com.customerglu.sdk.Modal.RegisterModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.messaging.FirebaseMessaging;


import java.util.HashMap;
import java.util.Map;

public class UserForm extends AppCompatActivity {

    Button submit;
    EditText userid,referid,name;
    String userID="";
    String referID="";
    String username="";
    String fcmToken="",customer_token="";
    CustomerGlu customerGlu;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_form);
        getFcmToken();
        init();
        getDeepLink();

    }
    private void getFcmToken() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        fcmToken = token;
                        System.out.println("token");
                        System.out.println(token);

                        // Log and toast
                    }
                });
    }

    private void initalizeCustomerGlu() {
        String deviceOs = String.valueOf(Build.VERSION.SDK_INT);
        Map<String,Object> registerData = new HashMap<>();
        registerData.put("userId",userID);
        registerData.put("userName","");
        registerData.put("firebaseToken",fcmToken);
        Map<String,String> profile = new HashMap<>();
        profile.put("firstName","him");
        registerData.put("profile",profile);
 
        customerGlu.registerDevice(this,registerData,true,new DataListner() {
                    //this method register your device and gives you token to retrieve data
                    @Override
                    public void onSuccess(RegisterModal registerModal) {
                        customer_token = registerModal.data.getToken();
                        Prefs.putKey(getApplicationContext(),"cus_token",customer_token);
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        Prefs.putKey(getApplicationContext(),"userID",userID);
                        Prefs.putKey(getApplicationContext(),"referID",referID);
                        Prefs.putKey(getApplicationContext(),"username",username);
                        startActivity(intent);

                    }

                    @Override
                    public void onFail(String message) {
                        Toast.makeText(getApplicationContext(), ""+message, Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    private void init() {

        submit = findViewById(R.id.submit);
        userid = findViewById(R.id.userid);
        referid = findViewById(R.id.referid);
        name = findViewById(R.id.name);
        customerGlu = CustomerGlu.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                userID = userid.getText().toString();
                referID = referid.getText().toString();
                username = name.getText().toString();

//                if(userID.equalsIgnoreCase(""))
//                {
//                    Toast.makeText(UserForm.this, "Please fill UserID", Toast.LENGTH_SHORT).show();
//                }
                 if(username.equalsIgnoreCase(""))
                {
                    Toast.makeText(UserForm.this, "Please fill UserName", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    String cus = Prefs.getKey(getApplicationContext(),"cus_token");
                    System.out.println("cust");
                    System.out.println(cus);
                    if(cus.isEmpty()||cus.equalsIgnoreCase("")||cus.equalsIgnoreCase(null)) {
                           initalizeCustomerGlu();

                    }
                    else
                    {
                        customer_token = cus;
                    }
                }
            }
        });

    }


    private void getDeepLink() {

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            referID =  MainActivity.customerGlu.getReferralId(deepLink);
                            referid.setText(referID);

                            //  Toast.makeText(MainActivity.this, ""+refer_id, Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("k", "getDynamicLink:onFailure", e);
                    }
                });
    }

}
