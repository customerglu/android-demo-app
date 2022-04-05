package com.customerglu.customerglu.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.customerglu.customerglu.MainActivity;
import com.customerglu.customerglu.MyCart;
import com.customerglu.customerglu.Prefs;

import com.customerglu.customerglu.R;
import com.customerglu.sdk.Modal.EventData;


import java.util.HashMap;


public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.UnderShop> {

    int size = 20;
    private final Context mContext;
    EventData eventData;
    EventData.EventProperties eventProperties;
    public GroceryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public GroceryAdapter.UnderShop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grocery_store, parent, false);
        UnderShop vh = new UnderShop(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryAdapter.UnderShop holder, int position) {
        try {

            holder.save.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    HashMap<String,Object> eventProperties = new HashMap();
                    eventProperties.put("state","1");
                    String user_id = Prefs.getKey(mContext,"userID");
                    System.out.println("Event send");
                    MainActivity.customerGlu.sendEvent(mContext,"completePurchase",eventProperties);

                    Intent intent = new Intent(mContext, MyCart.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });

        } catch (Exception e) {
            Toast.makeText(mContext, "" + e, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class UnderShop extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mProductname;
        CardView mCardView;
        Button save;

        public UnderShop(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.productImage);
            mProductname = itemView.findViewById(R.id.productName);
            mCardView = itemView.findViewById(R.id.imageCard);
            save = itemView.findViewById(R.id.save);
        }
    }
}
