package com.example.snehamishra.smartshopping;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Second extends AppCompatActivity {

    TextView tvName,tvDisplay;
    SharedPreferences sp1;
    Spinner spnHeight,spnHeight1;
    EditText etWeight;
    Button btnCalculatebmi,btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        int orientation= ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        setRequestedOrientation(orientation);



        spnHeight= (Spinner) findViewById(R.id.spnHeight);
        spnHeight1= (Spinner) findViewById(R.id.spnHeight1);
        etWeight= (EditText) findViewById(R.id.etWeight);
        btnCalculatebmi= (Button) findViewById(R.id.btnCalculateBmi);
        btnView= (Button) findViewById(R.id.btnView);
        tvDisplay= (TextView) findViewById(R.id.tvDisplay);

        sp1= getSharedPreferences("myp1",MODE_PRIVATE);

        String name= sp1.getString("n","");


        final Database db= new Database(this);




        final ArrayList<String> height = new ArrayList<>();
        height.add("1 ft");
        height.add("2 ft");
        height.add("3 ft");
        height.add("4 ft");
        height.add("5 ft");
        height.add("6 ft");
        height.add("7 ft");
        height.add("8 ft");

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,height);
        spnHeight.setAdapter(adapter);


        final ArrayList<String> height1 = new ArrayList<>();
        height1.add("0 inch");
        height1.add("1 inch");
        height1.add("2 inch");
        height1.add("3 inch");
        height1.add("4 inch");
        height1.add("5 inch");
        height1.add("6 inch");
        height1.add("7 inch");
        height1.add("8 inch");
        height1.add("9 inch");
        height1.add("10 inch");
        height1.add("11inch");
        height1.add("12 inch");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, height1);
        spnHeight1.setAdapter(adapter);

        btnCalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String weight= etWeight.getText().toString();
                if(weight.length()==0)
                {
                    etWeight.setError("enter weight");
                    etWeight.requestFocus();
                    return;
                }

                double s1 = Double.parseDouble(etWeight.getText().toString());
                double s2= (spnHeight.getSelectedItemPosition()+1);
                double s3=(spnHeight1.getSelectedItemPosition());
                double f1= (s2*12)+s3;

                double bmi = (s1 / (f1*f1*0.0254*0.0254));

                Intent i = new Intent(Second.this,Third.class);
                i.putExtra("bmi",bmi);
                startActivity(i);





            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d= db.viewData();
                tvDisplay.setText(d);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m1,menu);
        return super.onCreateOptionsMenu(menu);



    }

    public void back(View v)
    {
        startActivity(new Intent(Second.this,ImageActivity.class));
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.About)
        {

            Snackbar.make(findViewById(android.R.id.content),"developed by Sirisha", Snackbar.LENGTH_LONG).show();

        }

        if(item.getItemId()==R.id.Google)
        {
            Intent i= new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("http://www.google.com"));
            startActivity(i);

        }


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to close this application ?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();


    }


}









