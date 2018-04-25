package com.example.snehamishra.smartshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Pradeep on 7/13/2017.
 */

public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Context context;

    public Database(Context context)
    {
        super(context,"employeeDB",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String q= "create table employee(bmi float)";
        db.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



    }


    public void  addData(String n, String a, String p, Double bmi)
    {
        ContentValues cv= new ContentValues();
        cv.put("bmi",bmi);
        long rid= db.insert("employee",null,cv);
        if(rid<0)
        {
            Toast.makeText(context, " insert issue", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Record Inserted", Toast.LENGTH_SHORT).show();
        }

    }
     public String viewData()
     {
         StringBuffer sb= new StringBuffer();
         Cursor c= db.query("employee",null,null,null,null,null,null);
         c.moveToFirst();

         if(c.getCount()==0)
         {
             sb.append("No Record To Display");
         }
         else
         {
             do {
                 float bmi= c.getFloat(0);
                 sb.append("\n My BMI is : " + bmi + "\n\n-----------------------------------------------\n\n");
             }while (c.moveToNext());
         }
         return sb.toString();
     }
}
