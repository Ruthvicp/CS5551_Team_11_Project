package com.example.snehamishra.smartshopping;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText etName, etAge, etPhoneNumber;
    Button btnRegister;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        sp1 = getSharedPreferences("myp1", MODE_PRIVATE);
        String rn = sp1.getString("n","");
        String ra = sp1.getString("a","");
        String rp = sp1.getString("p","");

        if(rn.length() == 0 && ra.length() == 0 && rp.length() == 0)
        {
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name = etName.getText().toString();
                    String age = etAge.getText().toString();
                    String phonenumber = etPhoneNumber.getText().toString();


                    if (name.length() == 0) {
                        etName.setError("enter name");
                        etName.requestFocus();
                        return;

                    } else if (age.length() == 0) {
                        etAge.setError("enter age");
                        etAge.requestFocus();
                        return;
                    } else if (phonenumber.length() == 0 || phonenumber.length() != 10) {
                        etPhoneNumber.setError("enter valid phone no");
                        etPhoneNumber.requestFocus();
                        return;
                    }

                    SharedPreferences.Editor editor = sp1.edit();
                    editor.putString("n", name);
                    editor.putString("a", age);
                    editor.putString("p", phonenumber);
                    editor.commit();



                    Intent intent = new Intent(MainActivity.this, Second.class);
                    startActivity(intent);
                    finish();









                }
            });


        }

        else{

            Intent intent = new Intent(MainActivity.this, Second.class);
            startActivity(intent);
            finish();


        }

    }
}