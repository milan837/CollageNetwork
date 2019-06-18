package com.group.milan.collage.collagenetworking.SplashScreenActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.group.milan.collage.collagenetworking.HomeActivity.HomeActivity;
import com.group.milan.collage.collagenetworking.LoginActivity.LoginActivity;
import com.group.milan.collage.collagenetworking.R;

public class SplashScreenActivity extends AppCompatActivity {

    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences sharedPreferences=getSharedPreferences("user_login",MODE_PRIVATE);
        userId=sharedPreferences.getString("user_id",null);

        Thread splash=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1500);
                    if(userId==null){
                        Intent mainActivity=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }else{
                        Intent mainActivity=new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(mainActivity);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        splash.start();

    }

}
