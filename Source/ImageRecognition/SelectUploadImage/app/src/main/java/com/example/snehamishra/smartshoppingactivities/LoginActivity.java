package com.example.snehamishra.smartshoppingactivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class LoginActivity extends AppCompatActivity {

    //defining the variables
    private Button captureImage, selectImage;
    private ImageView imageDisplay;
    private TextView backActivity, skipActivity;
    private StorageReference firebaseStorage;

    private static final int GALLERY_INTENT = 2;

    private ProgressDialog progressDialog;

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

        progressDialog = new ProgressDialog(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            progressDialog.setMessage("Uploading ... ");
            progressDialog.show();

            Uri uri = data.getData();

            StorageReference filePath = firebaseStorage.child("Photos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(LoginActivity.this,"Upload Done", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(LoginActivity.this,"Upload Failed", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });

        }

    }
}
