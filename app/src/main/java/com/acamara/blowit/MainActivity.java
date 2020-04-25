package com.acamara.blowit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    private Button register;
    private static int TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(this);
        viewPager.setAdapter(myadapter);
        register = findViewById(R.id.signup);

    }
    public void gotoRegister(View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        },TIMEOUT);
    }
}