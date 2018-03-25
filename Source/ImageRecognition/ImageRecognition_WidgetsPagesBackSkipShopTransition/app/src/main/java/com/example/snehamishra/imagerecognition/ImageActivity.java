package com.example.snehamishra.imagerecognition;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    private Button captureImage, selectImage, analyzeImage, shopByImage;
    private ImageView imageDisplay;
    private TextView imageDescription, backActivity, skipActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        defineWidgets();

        backActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, LoginActivity.class));
            }
        });

        skipActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, ShoppingActivity.class));
            }
        });

        shopByImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, ShoppingActivity.class));
            }
        });


    }

    //assign the widgets
    public void defineWidgets(){
        captureImage = findViewById(R.id.capture_btn);
        selectImage  = findViewById(R.id.select_btn);
        analyzeImage  = findViewById(R.id.analyze_btn);
        shopByImage  = findViewById(R.id.shop_btn);
        imageDisplay = findViewById(R.id.imageView);
        imageDescription = findViewById(R.id.imageDescriptionTextView);
        backActivity = findViewById(R.id.backActivityTextView);
        skipActivity = findViewById(R.id.skipActivityTextView);

    }
}
