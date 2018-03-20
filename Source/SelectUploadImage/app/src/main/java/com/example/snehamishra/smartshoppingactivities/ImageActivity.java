package com.example.snehamishra.smartshoppingactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageActivity extends AppCompatActivity {

    //defining the variables
    private Button captureImage, selectImage;
    private ImageView imageDisplay;
    private TextView backActivity, skipActivity;
    private StorageReference firebaseStorage;

    private static final int GALLERY_INTENT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        // Initialising the variables defined and setting references
        firebaseStorage = FirebaseStorage.getInstance().getReference();
        captureImage = (Button) findViewById(R.id.capture_btn);

        selectImage = (Button)findViewById(R.id.select_btn);
        imageDisplay = (ImageView)findViewById(R.id.imageView);
        backActivity = (TextView)findViewById(R.id.backActivityTextView);
        skipActivity = (TextView)findViewById(R.id.SkipPageTextView);

        //create on click listener
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");

                startActivityForResult(intent,GALLERY_INTENT);
            }
        });

    }



}
