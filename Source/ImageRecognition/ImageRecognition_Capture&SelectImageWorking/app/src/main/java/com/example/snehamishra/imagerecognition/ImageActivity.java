package com.example.snehamishra.imagerecognition;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

public class ImageActivity extends AppCompatActivity {

    private Uri imageCapturedUri;
    private Button selectImage, captureImage, analyzeImage, shopByImage;
    private ImageView imageDisplay;
    private TextView imageDescription, backActivity, skipActivity;

    private VisionServiceClient visionServiceClient = new VisionServiceRestClient("11d5cb03ef0849cbbd107178b42ca683");

    private static final int CAMERA_INTENT = 1;
    private static final int GALLERY_INTENT = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        defineWidgets();

        //user's selection to move to login page
        backActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, LoginActivity.class));
            }
        });

        //user's selection to skip image recognition & move to shopping page
        skipActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, ShoppingActivity.class));
            }
        });

        //user's selection to use Image Analysis for shopping
        shopByImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, ShoppingActivity.class));
            }
        });

        //user chooses the image from gallery
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });

        //user captures image using camera
        captureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                captureImage();

            }
        });


        //user can analyze the selected image
        analyzeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(imageDisplay==null ){
                    Toast.makeText(getApplicationContext(), "No Image selected.", Toast.LENGTH_LONG).show();//display the text on image click event

                }
                final AsyncTask<InputStream, String, String> visionTask = new AsyncTask<InputStream, String, String>() {
                    ProgressDialog dialog = new ProgressDialog(ImageActivity.this);

                    @Override
                    protected String doInBackground(InputStream... inputStreams) {
                        try {
                            publishProgress("Analyzing...");
                            String[] details = {};
                            String[] features = {"Description"};

                            AnalysisResult result = visionServiceClient.analyzeImage(inputStreams[0], features, details);
                                String strResult = new Gson().toJson(result);
                                return strResult;


                            } catch (Exception e){
                                return null;
                            }
                        }

                        @Override
                        protected void onPreExecute() {
                            dialog.show();
                        }

                        @Override
                        protected void onPostExecute(String s) {
                            dialog.dismiss();

                            AnalysisResult result = new Gson().fromJson(s,AnalysisResult.class);

                            imageDescription = findViewById(R.id.imageDescriptionTextView);
                            StringBuilder stringBuilder = new StringBuilder();

                            for(Caption caption:result.description.captions){
                                stringBuilder.append(caption.text);

                            }
                            imageDescription.setText(stringBuilder);
                        }

                        @Override
                        protected void onProgressUpdate(String... values) {
                            dialog.setMessage(values[0]);
                        }

                    };

                   // visionTask.execute(inputStream);
            }
        });

    }

    private void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_INTENT);

    }

    private void captureImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA_INTENT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_INTENT){

                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                imageDisplay.setImageBitmap(bitmap);

            }
            if( requestCode == GALLERY_INTENT){
                imageCapturedUri = data.getData();
                imageDisplay.setImageURI(imageCapturedUri);

            }
        }
    }

    //assign the widgets
    public void defineWidgets(){
        captureImage = findViewById(R.id.capture_btn);
        selectImage = findViewById(R.id.select_btn);
        analyzeImage  = findViewById(R.id.analyze_btn);
        shopByImage  = findViewById(R.id.shop_btn);
        imageDisplay = findViewById(R.id.imageView);
        imageDescription = findViewById(R.id.imageDescriptionTextView);
        backActivity = findViewById(R.id.backActivityTextView);
        skipActivity = findViewById(R.id.skipActivityTextView);

    }
}
