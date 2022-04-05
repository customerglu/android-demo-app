package com.customerglu.customerglu;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabButton {

    @SuppressLint("ResourceType")
    public static void init(Activity activity){
       ViewGroup root = (ViewGroup) activity.getWindow().getDecorView();
        FloatingActionButton fab = new FloatingActionButton(activity.getApplicationContext());
        fab.setId(10095);
        fab.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        ));
        root.addView(fab);
    }
}