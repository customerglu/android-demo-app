package com.customerglu.customerglu;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.*;


import com.customerglu.sdk.CustomerGlu;
import com.customerglu.sdk.Utils.BannerAction;
import com.txusballesteros.bubbles.BubbleLayout;
import com.txusballesteros.bubbles.BubblesManager;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public  class MainActivity extends AppCompatActivity  {

    CardView wallet,rewards,shop,cart;
    View  logout,close;
    CardView dialog;
    String refer_id="";
    String user_id="";
    Button show;
    String user_name="";
    View view;
    BroadcastReceiver mMessageReceiver;
    Map<String,Object> exampleobj = new HashMap<>();
    @SuppressLint("StaticFieldLeak")
    public static CustomerGlu customerGlu;
    FragmentManager fragmentManager;
    private WindowManager mWindowManager;
    private View mFloatingView;
    private WindowManager.LayoutParams params;
    Boolean isInBackground;

    @Override
    protected void onDestroy() {
        Log.e("CustomerGlu"," Main Activity onDestroy");

    //    if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
        super.onDestroy();

    }



    @RequiresApi(api = VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("CustomerGlu","main");
        customerGlu = CustomerGlu.getInstance();
     //   FabButton.init(this);

//         showFloatingButton();
//        ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
//        ActivityManager.getMyMemoryState(myProcess);
//        isInBackground = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
//        if(isInBackground) {
//            Log.d("Tutorialspoint.com","Your application is in background state");
//        }else{
//            Log.d("Tutorialspoint.com","Your application is in foreground state");
//        }

        //  customerGlu.enablePreaching(getApplicationContext());
      //  customerGlu.enableAnalyticsEvent(true);
      //  customerGlu.autoCloseWebview(true);
        customerGlu.setDefaultBannerImage(getApplicationContext(),"https://assets.customerglu.com/demo/quiz/banner-image/Quiz_2.png");
        customerGlu.configureLoaderColour(getApplicationContext(),"#5c3700B3");
         mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Extract data included in the Intent
                try {

                    System.out.println("==================Recieved============");
                    if(intent.getAction().equalsIgnoreCase("CUSTOMERGLU_DEEPLINK_EVENT"))
                    {

                    String data =  intent.getStringExtra("data");
                    JSONObject jsonObject = new JSONObject(data);
                    String message = jsonObject.getString("deepLink");
                  //  Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    }
                    if(intent.getAction().equalsIgnoreCase("CUSTOMERGLU_ANALYTICS_EVENT"))
                    {

                        String data =  intent.getStringExtra("data");
                        JSONObject jsonObject = new JSONObject(data);
                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }

        };
        registerReceiver(mMessageReceiver,new IntentFilter("CUSTOMERGLU_DEEPLINK_EVENT"));
        registerReceiver(mMessageReceiver,new IntentFilter("CUSTOMERGLU_ANALYTICS_EVENT"));

        // customerGlu.disableGluSdk(false);

        user_id = Prefs.getKey(getApplicationContext(),"userID");
        refer_id = Prefs.getKey(getApplicationContext(),"referID");
        user_name = Prefs.getKey(getApplicationContext(),"username");
        System.out.println("userID");
        System.out.println(user_id);
    //    createBanner(R.id.cg_banner);
        findViews();
    //    customerGlu.webPreLoading(this);
     //   onButtonShowPopupWindowClick(this.show);
    }





    @Override
    protected void onStop() {
        Log.e("Cust","Stop");

     //   if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
        super.onStop();

    }

    @Override
    protected void onPause() {
        Log.e("Cust","onPause");
     //   if (mFloatingView != null) mWindowManager.removeView(mFloatingView);
        super.onPause();

    }

    @Override
    protected void onResume() {
        Log.e("Cust","onResume");
        super.onResume();
    }


    @RequiresApi(api = VERSION_CODES.M)
    @Override
    protected void onStart() {

        super.onStart();
    }

    private void findViews() {
        wallet = findViewById(R.id.wallet);
        rewards = findViewById(R.id.rewards);
        shop = findViewById(R.id.shop);
        cart = findViewById(R.id.cart);
        logout = findViewById(R.id.logout);
        dialog = findViewById(R.id.dialog);
       close = findViewById(R.id.close);
       show = findViewById(R.id.show);
       exampleobj.put("key","value");
       show.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.setVisibility(View.VISIBLE);
               show.setVisibility(View.GONE);
           }
       });
       close.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.setVisibility(View.GONE);
               show.setVisibility(View.VISIBLE);
           }
       });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customerGlu.clearGluData(getApplicationContext());
                Prefs.putKey(getApplicationContext(),"cus_token","");
                Intent intent = new Intent(getApplicationContext(), UserForm.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                customerGlu.openWallet(getApplicationContext());

            }
        });

        rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


         //       customerGlu.loadAllCampaigns(getApplicationContext());
                customerGlu.loadCampaignById(getApplicationContext(),"042a1048-569e-47c8-853c-33af1e325c93");
//                Map<String,Object> param = new HashMap<>();
//                param.put("type","quiz");
//                param.put("status","pristine");
//                customerGlu.loadCampaignsByFilter(getApplicationContext(),param);

            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ShoppingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyCart.class);
////                intent.putExtra("nudge_url","https://harjotscs.github.io/contactmanager/");
////               intent.putExtra("nudge_url","https://dbtailwi34eql.cloudfront.net/reward/?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJnbHV0ZXN0LWg5IiwiZ2x1SWQiOiIxZWY2YTJlNS00YThhLTRiMzEtYjI0ZC1mZjEzNmI2Nzk2ZDQiLCJjbGllbnQiOiI4NGFjZjJhYy1iMmUwLTQ5MjctODY1My1jYmEyYjgzODE2YzIiLCJkZXZpY2VJZCI6ImRldmljZWIiLCJkZXZpY2VUeXBlIjoiYW5kcm9pZCIsImlhdCI6MTYzOTk5ODkxMywiZXhwIjoxNjcxNTM0OTEzfQ.k17Xhzq3set_BJo7hN_92xzSckaaBr9NFoaq8CHFJs8&rewardUserId=9386d402-0f0d-484b-9f97-dda2f0c34000");
////                intent.putExtra("opacity", "0.6");
//
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });

    }

}