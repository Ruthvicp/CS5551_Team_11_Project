package com.example.snehamishra.smartshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Animation extends AppCompatActivity {

    ImageView im1;
    android.view.animation.Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);


        im1= (ImageView) findViewById(R.id.im1);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.custom);
        im1.startAnimation(animation);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Intent i= new Intent(Animation.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();
    }
}


