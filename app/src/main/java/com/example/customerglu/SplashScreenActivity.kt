package com.example.customerglu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.example.customerglu.LoginActivity
import com.example.customerglu.R
import com.example.customerglu.Utils.Prefs


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        Handler().postDelayed({

            checkUser()

        }, 1000)

    }

    private fun checkUser() {
        /*
        if(FirebaseUtils.firebaseUser?.isEmailVerified == true){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
*/
       var userId =  Prefs.getKey(applicationContext,"userId");
        if(userId!= null && !userId.isEmpty()){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            val intent = Intent(this, LoginOptionActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}