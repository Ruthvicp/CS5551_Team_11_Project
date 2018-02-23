package com.umkc.team11.smartshop;

import com.android.volley.AuthFailureError;
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

/**
 * Created by camle on 2/19/2018.
 */

public class SearchAPI {

    public SearchAPI()
    {

    }

    public ArrayList<String> searchAPIs(String searchText)
    {
        String url ="https://api.macys.com/v4/catalog/search?searchphrase=" + searchText;
        Map<String, String>  params = new HashMap<String, String>();
        params.put("x-macys-webservice-client-id", "x94qx87knta48h9g48n589pv");
        params.put("Accept", "application/json");
        ArrayList<String> list = new ArrayList<>();

        CustomRequest jsObjRequest = new CustomRequest
                (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jarray = response.getJSONArray("searchresultgroups");

                            for(int i = 0; i < jarray.length(); i++)
                            {
                                JSONObject item = jarray.getJSONObject(i);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        String err = "Error";
                    }
                });

        return list;

    }
}
