package com.example.snehamishra.imagerecognition;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import java.io.File;
import java.net.URI;

public class ImageActivity extends AppCompatActivity {

    private Uri imageCapturedUri;
    private Button chooseImage, analyzeImage, shopByImage;
    private ImageView imageDisplay;
    private TextView imageDescription, backActivity, skipActivity;

    private static final int CAMERA_INTENT = 1;
    private static final int GALLERY_INTENT = 2;



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

        // user's selection to choose image (from camera or gallery)
        final String[] items = new String[] {"From Camera", "From Gallery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image");

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment.getExternalStorageDirectory(),"tmp_avatar"+String.valueOf(System.currentTimeMillis())+".jpg");
                    imageCapturedUri = Uri.fromFile(file);
                    try{
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCapturedUri);
                        intent.putExtra("Return Data", true);

                        startActivityForResult(intent,CAMERA_INTENT);

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    dialog.cancel();
                }else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Complete Action using "),GALLERY_INTENT);
                }
            }
        });
        final AlertDialog dialog = builder.create();

        //user chooses the image
        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });

        //user's selection to use Image Analysis for shopping
        shopByImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageActivity.this, ShoppingActivity.class));
            }
        });


        //user can anlyze the selected image
        analyzeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK){
            return;

        }
        Bitmap bitmap = null;
        String path;

        if(requestCode == GALLERY_INTENT){
            imageCapturedUri = data.getData();
            path = getRealPathFromURI(imageCapturedUri);

            if(path==null){
                path=imageCapturedUri.getPath();
            }
            if(path!=null){
                bitmap= BitmapFactory.decodeFile(path);

            }
        }else {
            path=imageCapturedUri.getPath();
            bitmap=BitmapFactory.decodeFile(path);

        }
        imageDisplay.setImageBitmap(bitmap);

    }

    public String getRealPathFromURI(Uri contentURI){
        String[] project = {MediaStore.Images.Media.DATA};

        Cursor cursor = managedQuery(contentURI, project, null, null, null);

        if(cursor == null){
            return null;
        }
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    //assign the widgets
    public void defineWidgets(){
        chooseImage = findViewById(R.id.chooseImage_btn);
        analyzeImage  = findViewById(R.id.analyze_btn);
        shopByImage  = findViewById(R.id.shop_btn);
        imageDisplay = findViewById(R.id.imageView);
        imageDescription = findViewById(R.id.imageDescriptionTextView);
        backActivity = findViewById(R.id.backActivityTextView);
        skipActivity = findViewById(R.id.skipActivityTextView);

    }
}
