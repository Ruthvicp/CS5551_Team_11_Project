package com.umkc.team11.smartshop;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingActivity extends AppCompatActivity {

    private SearchData sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sql = SearchData.getInstance(this);

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
        ArrayList<ItemData> searchReturn = sql.getShoppingItems(searchPhrase);
        // find the list view
        ListView lv = findViewById(R.id.lst_items);

        // place the data into the list
        lv.setAdapter(new ItemAdapter(this, searchReturn));
        //searchAPIs(searchPhrase);
    }

    public ArrayList<String> searchAPIs(String searchText)
    {
        String url ="https://api.indix.com/v2/summary/products?countryCode=US&q=" + searchText + "&app_key=w2xqtl4uBXLJnCk0zscGrt86TEh80bmx";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Accept", "application/json");
        ArrayList<String> list = new ArrayList<>();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        /*try {
                            //JSONArray jarray = response.getJSONArray("searchresultgroups");

                            for(int i = 0; i < jarray.length(); i++)
                            {
                                JSONObject item = jarray.getJSONObject(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error");
                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        return list;

    }

}
