package com.testexamples.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.testexamples.R;
import com.testexamples.databinding.ActivitySplashScreenBinding;

/*Author :Ganesh
*  * This is the First Screen user will see everytime at the time of Launch (5sec)
 */

public class ActivitySplashScreen extends AppCompatActivity {
    Handler handler;
    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(ActivitySplashScreen.this, R.layout.activity_splash_screen);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ActivitySplashScreen.this, ActivityImagesList.class);
                startActivity(intent);
                finish();
            }
        },5000);
    }
}