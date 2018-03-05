package com.umkc.team11.smartshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {

    //private SearchAPI search;
    private SearchData sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sql = SearchData.getInstance(this);

        //search = new SearchAPI();

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
        //search.searchAPIs(searchPhrase);
    }

}
