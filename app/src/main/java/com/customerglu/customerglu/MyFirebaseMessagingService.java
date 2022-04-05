package com.customerglu.customerglu;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        JSONObject data = null;
        JSONObject json = new JSONObject(remoteMessage.getData());

                MainActivity.customerGlu.displayCustomerGluNotification(this,json, R.drawable.customerglu,0.5);

        }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

    }

    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
        Toast.makeText(getApplicationContext(), "my ex"+e, Toast.LENGTH_SHORT).show();
    }
}

