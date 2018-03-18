package com.example.snehamishra.smartshoppingimagerecognition;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ImageRecognition extends AppCompatActivity {


    private Button analyzeImage;
    private TextView descriptionView;
    private ImageView imageView;
    private VisionServiceClient visionServiceClient = new VisionServiceRestClient("11d5cb03ef0849cbbd107178b42ca683");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_recognition);

        // assign variables
        analyzeImage = (Button)findViewById(R.id.analyzeImage_btn);
        imageView = (ImageView)findViewById(R.id.imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.sneha1);

        imageView.setImageBitmap(bitmap);

        //convert image to stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


        analyzeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AsyncTask<InputStream, String, String> visionTask = new AsyncTask<InputStream, String, String>() {
                    ProgressDialog dialog = new ProgressDialog(ImageRecognition.this);

                    @Override
                    protected String doInBackground(InputStream... inputStreams) {
                        try {
                            publishProgress("Analyzing...");
                            String[] details = {};
                            String[] features = {"Description"};

                            AnalysisResult  result = visionServiceClient.analyzeImage(inputStreams[0], features, details);

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

                        descriptionView = (TextView)findViewById(R.id.descriptionTextView);
                        StringBuilder stringBuilder = new StringBuilder();

                        for(Caption caption:result.description.captions){
                            stringBuilder.append(caption.text);

                        }
                        descriptionView.setText(stringBuilder);
                    }

                    @Override
                    protected void onProgressUpdate(String... values) {
                        dialog.setMessage(values[0]);
                    }

                };

                visionTask.execute(inputStream);
            }
        });


    }
}
