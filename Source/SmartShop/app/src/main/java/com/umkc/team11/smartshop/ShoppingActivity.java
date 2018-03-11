package com.umkc.team11.smartshop;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingActivity extends AppCompatActivity {

    //private SearchData sql;
    private ArrayList<ItemData> clothData;
    private Context appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appContext = this;

        //sql = SearchData.getInstance(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void searchCall(View v)
    {
        EditText searchText = findViewById(R.id.txt_search);
        String searchPhrase = String.valueOf(searchText.getText());
        searchAPIs(searchPhrase);
    }

    public void searchAPIs(String searchText)
    {
        String url ="https://api.indix.com/v2/summary/products?countryCode=US&q=" + searchText + "&app_key=w2xqtl4uBXLJnCk0zscGrt86TEh80bmx";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Accept", "application/json");
        ArrayList<String> list = new ArrayList<>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try {
                            JSONObject res = response.getJSONObject("result");
                            JSONArray jarray = res.getJSONArray("products");
                            clothData = new ArrayList<>();

                            for(int i = 0; i < jarray.length(); i++)
                            {
                                JSONObject item = jarray.getJSONObject(i);
                                String name = item.getString("title");
                                String brand = item.getString("brandName");
                                double price = Double.parseDouble(item.getString("maxSalePrice"));
                                String image = item.getString("imageUrl");

                                clothData.add(new ItemData(name, brand, price, image));
                            } // end loop

                            // find the list view
                            ListView lv = findViewById(R.id.lst_items);

                            // place the data into the list
                            lv.setAdapter(new ItemAdapter(appContext, clothData));
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        } // end try/catch
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error");
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    } // end searchAPIs

}
